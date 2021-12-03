package autoutil.paths;

import geometry.Circle;
import geometry.CircleArc;
import geometry.Point;
import global.Constants;

public class Arc extends PathSegment{
    private final CircleArc arc;

    public Arc(Circle generated, Point startPoint, Point endPoint) {
        arc = new CircleArc(generated.center, startPoint, endPoint, generated.r);
    }

    @Override
    public void generatePoints() {
        points.addAll(arc.getPoints(Constants.ANG_ACC_ARC));
    }
}
