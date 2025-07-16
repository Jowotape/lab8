package frame;

import prob1.*;
import prob2.*;
import prob3.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JotoFrame extends JFrame implements ActionListener {

    JButton button1;
    JButton button2;
    JButton button3;

    public JotoFrame(){

        button1 = new JButton("Problema 1");
        button2 = new JButton("Problema 2");
        button3 = new JButton("Problema 3");
        button1.setBounds(200, 100, 100, 50);
        button2.setBounds(200, 160, 100, 50);
        button3.setBounds(200, 220, 100, 50);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(button1);
        this.add(button2);
        this.add(button3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){ //Esto ejecutará el problema 1
            Problema1 p1 = new Problema1();
            p1.setVisible(true);
        }
        if(e.getSource()==button2){
            Prob2 p2 = new Prob2();
            p2.setVisible(true);
        }
        if(e.getSource()==button3){
            Problema3 p3 = new Problema3();
        }
    }
}
