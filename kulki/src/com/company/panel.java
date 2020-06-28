package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Ellipse2D;



public class panel extends JPanel {

    private List<kula> kulki;
    private int size = 20;
    private Timer timer;
    private static final int DELAY = 33;

    public panel() {
        kulki = new ArrayList<>();
        setBackground(Color.WHITE);
        listener L = new listener();
        addMouseListener(L);
        addMouseMotionListener(L);
        addMouseWheelListener(L);
        timer = new Timer(DELAY, L);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (kula k : kulki) {
            g.setColor(k.color);
            g.fillOval(k.x, k.y, k.size, k.size);
        }
        g.setColor(Color.red);
        g.drawString(Integer.toString(kulki.size()), 40, 40);
    }



    private class listener implements MouseWheelListener, MouseListener, ActionListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            kula k = new kula(getMousePosition().x - size/2, getMousePosition().y - size/2, size);
          //  if(!k.collisionTest()){
                kulki.add(k);
                repaint();
           // }

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            for (kula kula : kulki) {
                kula.update();
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            kula k = new kula(getMousePosition().x - size/2, getMousePosition().y - size/2, size);
            if(!k.collisionTest()){
                kulki.add(k);
               // repaint();
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            size = size - e.getWheelRotation();
            // for (kula kula : kulki) {
            //     kula.size = kula.size - e.getWheelRotation();
            // }
            
        }
    }

    private class kula {

        private int x , y, size, xspeed, yspeed;
        private Color color;
        private static final int MAX_SPEED = 5;

        public kula(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
            xspeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
            yspeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
            xspeed = xspeed == 0 ? 1 : xspeed;
            yspeed = yspeed == 0 ? 1 : yspeed;

        }
        public void update(){
            x += xspeed;
            y += yspeed;

            if(x <= 0 || x >= getWidth() - this.size) xspeed = -xspeed;
            if(y <= 0 || y >= getHeight()- this.size) yspeed = -yspeed;

            this.checkForCollisions();
        }

        private void checkForCollisions() {
            Ellipse2D e1 = new Ellipse2D.Double(x, y, size, size);
            for (int i = kulki.indexOf(this) + 1; i < kulki.size(); i++) {
                kula temp = kulki.get(i);
                Ellipse2D e2 = new Ellipse2D.Double(temp.x, temp.y, temp.size, temp.size);
                //double pos = Math.pow((x+(size/2)) - (temp.x+(size/2)), 2) + Math.pow((y+(size/2)) - (temp.y+(size/2)), 2);
                
                if(e1.intersects(e2.getFrame())){
                    this.handleCollison(temp);
                } 
            }
        }

        private void handleCollison(kula temp){
            xspeed = - xspeed;
            yspeed = -yspeed;
            temp.xspeed = - temp.xspeed;
            temp.yspeed = - temp.yspeed;

        }
        public boolean collisionTest ()
        {
            for(int i = 0; i < kulki.size(); i++)
            {
                kula next = kulki.get(i);
                int distSqr = ((this.x - next.x)*(this.x - next.x) + (this.y - next.y)*(this.y - next.y));
                if((this.size*this.size)+(next.size*next.size) >= distSqr)
                    return true;
            }
            return false;
        }
       
    }
}
