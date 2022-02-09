package autoutil.generators;

import autoutil.paths.Path;

public abstract class Generator {

    protected Path path = new Path();

    public Path getPath() {
        return path;
    }

}
