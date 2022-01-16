package robotparts.sensors;

import automodules.stage.Exit;
import elements.GameElement;
import robotparts.RobotPart;
import robotparts.electronics.input.IColor;

/**
 * NOTE: Uncommented
 */

public class ColorSensors extends RobotPart {
    private IColor cso;

    // TODO NEW
    // Make all the sensor robot parts and make sure they work (also the servo for odometry fix)

    @Override
    public void init() {
        cso = createColorSensor("cso");
    }

    public float[] getOuttakeColorHSV(){
        float[] color = new float[3];
        android.graphics.Color.RGBToHSV(cso.getRed(), cso.getGreen(), cso.getBlue(), color);
        return color;
    }

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

    public boolean isBall(){
        return getFreightType().equals(GameElement.BALL);
    }
    public boolean isCube(){
        return getFreightType().equals(GameElement.CUBE);
    }

    public boolean isFreight(){ GameElement element = getFreightType(); return element.equals(GameElement.BALL) || element.equals(GameElement.CUBE); }
    public Exit exitFreight(){return new Exit(this::isFreight);}

}
