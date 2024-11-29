import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class DynamicSprite extends SolidSprite {
    private boolean isWalking =true;
    private double speed = 5;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame= 250;
    private Direction direction = Direction.EAST;

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    @Override
    public void draw(Graphics g) {
        int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn);

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }
    private void move() {
        switch (direction) {
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y += speed;
            }
            case EAST -> {
                this.x += speed;
            }
            case WEST -> {
                this.x -= speed;
            }
        }
    }
    private boolean isMovingPossible(ArrayList<SolidSprite> environment) {
        // Création de la hitbox pour le mouvement
        Rectangle2D.Double hitBox = new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());

        // Déplacement de la hitbox en fonction de la direction
        switch (direction) {
            case NORTH -> hitBox.y -= speed;
            case SOUTH -> hitBox.y += speed;
            case WEST -> hitBox.x -= speed;
            case EAST -> hitBox.x += speed;
        }

        // Vérification des collisions avec les sprites solides
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite solidSprite && !solidSprite.equals(this)) {
                Rectangle2D.Double solidHitBox = new Rectangle2D.Double(sprite.getX(), sprite.getY(),
                        sprite.getWidth(), sprite.getHeight());

                // Vérification de l'intersection avec la hitbox déplacée
                if (hitBox.intersects(solidHitBox)) {
                    // Si collision, ajuster la position du sprite
                    switch (direction) {
                        case NORTH -> this.y = solidHitBox.getY() + solidHitBox.getHeight(); // Arrêter devant
                        case SOUTH -> this.y = solidHitBox.getY() - this.height; // Arrêter devant
                        case WEST -> this.x = solidHitBox.getX() + solidHitBox.getWidth(); // Arrêter devant
                        case EAST -> this.x = solidHitBox.getX() - this.width; // Arrêter devant
                    }
                    return false; // Collision détectée
                }
            }
        }
        return true; // Pas de collision
    }

    public void moveIfPossible(ArrayList<SolidSprite> environment) {
        if (isMovingPossible(environment)) {
            move(); // Déplacer le sprite si le mouvement est possible
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}