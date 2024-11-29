import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GameRender extends JPanel{
    private List<Displayable> renderList;

    public GameRender() {
        renderList = new ArrayList<>();
        setBackground(java.awt.Color.BLACK);
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
    public void update() {
        repaint();
    }
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Displayable displayable : renderList) {
            displayable.draw(g);
        }
    }

}