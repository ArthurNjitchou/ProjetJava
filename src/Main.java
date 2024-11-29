import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
    private JFrame displayZoneFrame;
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine; // Déclaration de physicEngine
    private GameRender gameRender; // Déclaration de gameRender

    public Main() throws Exception {
        displayZoneFrame = new JFrame("The Best Game");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Chargement de l'image pour le héros
        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        renderEngine = new RenderEngine();
        gameEngine = new GameEngine(hero);
        physicEngine = new PhysicEngine();

        // Création du Timer pour le rendu
        Timer renderTimer = new Timer(50, e -> renderEngine.update());
        Timer gameTimer = new Timer(50, e -> gameEngine.update());
        Timer physicTimer = new Timer(50, e -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        gameRender = new GameRender();
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        Playground level = new Playground("./data/level1.txt");

        // Ajout d'un arbre à la scène
        Sprite test = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/tree.png")), 64, 64);
        for (Displayable displayable : level.getSpriteList())
        renderEngine.addToRenderList(displayable);
        renderEngine.addToRenderList(test);
        renderEngine.addToRenderList(hero);
        ArrayList<DynamicSprite> movingSprites = new ArrayList<>();
        movingSprites.add(hero);
        physicEngine.addToMovingSpriteList(movingSprites);
        physicEngine.setEnvironment(level.getSolidSpriteList());
        displayZoneFrame.addKeyListener(gameEngine);


        // Ajout d'un rocher solide
        SolidSprite testSprite = new SolidSprite(250, 300,
                ImageIO.read(new File("./img/rock.png")), 64, 64);
        renderEngine.addToRenderList(testSprite);

        // Ajout du héros à la liste des sprites à déplacer
        physicEngine.addMovingSprite(hero);
        ArrayList<SolidSprite> environment = new ArrayList<>();
        environment.add(testSprite);
        physicEngine.setEnvironment(environment);

        // Ajout du key listener pour le jeu
        displayZoneFrame.addKeyListener(gameEngine);

        // Timer pour afficher un message chaque seconde
        Timer timer = new Timer(1000000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("timer!");
            }
        });
        timer.start(); // Démarrage du timer

        // Pour arrêter le timer après 5 secondes
        new Timer(500000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                System.out.println("Timer arrêté.");
                displayZoneFrame.dispose();
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
        new Main(); // Instanciation de Main
    }
}