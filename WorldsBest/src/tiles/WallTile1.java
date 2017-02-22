package tiles;

import gfx.Assets;

public class WallTile1 extends Tile {
    
    public WallTile1(int id) {
        super(Assets.wall1, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
