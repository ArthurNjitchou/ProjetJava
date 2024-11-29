import javax.swing.*;
        import java.awt.*;
        import java.util.ArrayList;
        import java.util.List;
public class RenderEngine extends JPanel implements Engine{

    private List<Displayable> renderList;

    public RenderEngine() {
        renderList = new ArrayList<>();
    }
    public void setRenderList(List<Displayable> renderList) {
        this.renderList = renderList;
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }
    }
    int frames;
    int fps;
    int lastTime;
    public void update() {
        long currentTime = System.nanoTime();

        frames++;

        if (currentTime - lastTime >= 1000000000L) {
            fps = frames;
            frames = 0;
            lastTime = (int) currentTime;
        }

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }
    }

}