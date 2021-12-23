package robotparts.sensors;

import automodules.stage.Exit;
import elements.GameElement;
import robotparts.RobotPart;
import robotparts.electronics.IColor;

import static global.General.bot;

public class ColorSensors extends RobotPart {
    private IColor cso;

    @Override
    public void init() {
        cso = createColorSensor("cso");
    }

    public float[] getOuttakeColorHSV(){
        float[] color = new float[3];
        android.graphics.Color.RGBToHSV(cso.getRed(), cso.getGreen(), cso.getBlue(), color);
        return color;
    }

//    public float[] getOutakeColorRGB(){
//        return new float[]{(float)cso.red()*255/800, (float)cso.green()*255/800, (float)cso.blue()*255/800};
//    }

    public boolean isBall(){
        return getFreightType().equals(GameElement.BALL);
    }
    public boolean isCube(){
        return getFreightType().equals(GameElement.CUBE);
    }

    public boolean isFreight(){ GameElement element = getFreightType(); return element.equals(GameElement.BALL) || element.equals(GameElement.CUBE); }

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
    public Exit exitFreight(){return new Exit(this::isFreight);}

}
