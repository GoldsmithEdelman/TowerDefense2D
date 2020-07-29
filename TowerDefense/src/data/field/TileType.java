package data.field;

// pay attention to "enum", not a class! 
public enum TileType
{
    // actual tile types, framework for tiles, different keys/atributes can be added to a tile(like enemies can go over it, can projectiles go through) - buildable and texture for now
    Grass("grass", true), Water("water", false), Dirt("dirt",
            true),Menu("Menu", true), NULL("water", false),SPAWN("spawn", false),EXIT("exit", false), EditorBar("menubar", false), EditorAusgewahlt("ausgewaehlt", false); 

    String _textureName;
    boolean _buildable; //wheter tower can be buil on it

    TileType(String textureName, boolean buildable)
    {
        this._textureName = textureName;
        this._buildable = buildable;
    }
}
