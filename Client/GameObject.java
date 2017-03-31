import java.awt.Graphics;
public abstract class GameObject {

    protected int x, y;
    protected final String id;
    
    public GameObject(int x, int y, String id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    
    public abstract void draw(Graphics g);

    public void setX(int x){
        this.x = x;
    }

     public void setY(int y){
        this.y = y;
    }

     public int getX(){
        return x;
    }

     public int getY(){
        return y;
    }

	public String getId() {
		return id;
	}
}