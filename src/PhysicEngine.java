import java.util.ArrayList;

public class PhysicEngine implements Engine{

    private final ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<SolidSprite> environment;

    public PhysicEngine() {
        this.movingSpriteList = new ArrayList<>();
        this.environment = new ArrayList<>();
    }

    // Méthode pour ajouter un élément à movingSpriteList
    public void addMovingSprite(DynamicSprite sprite) {
        movingSpriteList.add(sprite);
    }

    public void addToMovingSpriteList(ArrayList<DynamicSprite> spriteArrayList){
        movingSpriteList.addAll(spriteArrayList);
    }
    // Setter pour la liste d'environnement
    public void setEnvironment(ArrayList<SolidSprite> environment) {
        this.environment = environment;
    }

    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(environment); // Tente de déplacer le sprite
        }
    }
}