package robotparts.sensors;

import automodules.stage.Exit;
import elements.GameElement;
import robotparts.RobotPart;
import robotparts.electronics.input.IColor;


public class ColorSensors extends RobotPart {
    /**
     * Color sensor input for intake
     */
    private IColor cso;

    // TODO4 NEW
    // Make more methods

    @Override
    public void init() {
        cso = createColorSensor("cso");
    }

    /**
     * Get the color that is sensed in HSV format
     * @return hsv color
     */
    public float[] getOuttakeColorHSV(){
        float[] color = new float[3];
        android.graphics.Color.RGBToHSV(cso.getRed(), cso.getGreen(), cso.getBlue(), color);
        return color;
    }

    /**
     * Get the type of freight being sensed
     * @return freight type
     */
    public GameElement getFreightType(){
        float h = getOuttakeColorHSV()[0];
        if(155 < h && h < 170){
            return GameElement.BALL;
        }else if(60 < h && h < 90){
            return GameElement.CUBE;
        }else{
            return GameElement.NONE;
        }
    }

    /**
     * Is a ball being sensed?
     * @return is ball
     */
    public boolean isBall(){
        return getFreightType().equals(GameElement.BALL);
    }

    /**
     * Is a cube being sensed?
     * @return is cube
     */
    public boolean isCube(){
        return getFreightType().equals(GameElement.CUBE);
    }

    /**
     * Is there a freight at all?
     * @return is freight
     */
    public boolean isFreight(){
        GameElement element = getFreightType();
        boolean hasFreightNear = element.equals(GameElement.BALL) || element.equals(GameElement.CUBE);
        return hasFreightNear && cso.getDistance() < 4;
    }
    public Exit exitFreight(){return new Exit(this::isFreight);}

}
