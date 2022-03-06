package auton;

import autoutil.generators.Generator;
import autoutil.reactors.Reactor;

public class AutoSegment<R extends Reactor, G extends Generator> {
    public final R reactor;
    public final G generator;
    public AutoSegment(R r, G g){
        reactor = r;
        generator = g;
    }

}
