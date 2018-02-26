/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import Enigma.Rotor.LargeRotor;
import Enigma.Rotor.MedRotor;
import Enigma.Rotor.SmallRotor;

public class Enigma extends Frame {

    String FileExtension = ".enigma";

    public static final int FRAME_WIDTH = 660;

    public static final int FRAME_HEIGHT = 400;

    public static Enigma screen;

    private final Insets ins;

    private SmallRotor smrotor = new SmallRotor();
    private MedRotor medrotor = new MedRotor();
    private LargeRotor lgrotor = new LargeRotor();

    protected TextArea Message = new TextArea();
    protected TextArea Encrypted = new TextArea();

    protected TextField messFN = new TextField();
    protected TextField encFN = new TextField();

    protected Label FNLabel = new Label("Enter a FileName", Label.CENTER);
    protected Label FNLabel2 = new Label("Enter a FileName", Label.CENTER);

    public Enigma() {
        this.FileExtension = ".enigma";
        setTitle("Enigma Simulator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        setLayout(new FlowLayout());

        add(Message);
        Panel p = new Panel();
        p.setLayout(new GridLayout(5, 1));
        p.add(new ButtonAdapter("Encrypt") {
            @Override
            public void pressed() {
                EncryptEnigma();
            }
        });
        p.add(FNLabel);
        p.add(messFN);
        p.add(new ButtonAdapter("Load") {
            @Override
            public void pressed() {
                LoadMessage(messFN.getText());
            }
        });
        p.add(new ButtonAdapter("Save") {
            @Override
            public void pressed() {
                SaveMessage(messFN.getText());
            }
        });

        add(p);

        add(Encrypted);
        Panel p2 = new Panel();
        p2.setLayout(new GridLayout(5, 1));
        p2.add(new ButtonAdapter("Decrypt") {
            @Override
            public void pressed() {
                DecryptEnigma();
            }
        });
        p2.add(FNLabel2);
        p2.add(encFN);
        p2.add(new ButtonAdapter("Load") {
            @Override
            public void pressed() {
                LoadCypherTxt(encFN.getText());
            }
        });
        p2.add(new ButtonAdapter("Save") {
            @Override
            public void pressed() {
                SaveCypherTxt(encFN.getText());
            }
        });

        add(p2);

        addMouseListener(new MouseKeeper());
        addWindowListener(new WindowKeeper());

        ins = getInsets();

    }

    public void EncryptEnigma() {
        System.out.println("Encrypting Enigma");
        String plain = Message.getText();
        plain = plain.toUpperCase();
        char[] cypher = new char[10000];

        for (int i = 0; i < plain.length(); i++) {
            cypher[i] = EncryptChar(plain.charAt(i));

        }

        Encrypted.setText(String.copyValueOf(cypher));

        smrotor = new SmallRotor();
        medrotor = new MedRotor();
        lgrotor = new LargeRotor();

    }

    public void DecryptEnigma() {
        System.out.println("Decrypting Enigma");
        String cypher = Encrypted.getText();
        cypher = cypher.toUpperCase();
        char[] plaintxt = new char[10000];

        for (int i = 0; i < cypher.length(); i++) {
            plaintxt[i] = DecryptChar(cypher.charAt(i));

        }

        Message.setText(String.copyValueOf(plaintxt));

        smrotor = new SmallRotor();
        medrotor = new MedRotor();
        lgrotor = new LargeRotor();
    }

    public void LoadMessage(String FileName) {
        System.out.println(FileName + FileExtension);
        try {
            FileInputStream in = new FileInputStream(FileName + FileExtension);
            DataInputStream din = new DataInputStream(in);

            char[] mess = new char[10000];

            try {
                int i = 0;
                while (true) {
                    mess[i] = (char) din.readByte();
                    System.out.println("Received a |" + mess[i] + "|");
                    i++;
                }
            } catch (IOException e) {
                Message.setText(String.valueOf(mess));

            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            Message.setText("Can't Find File " + FileName + FileExtension);
        }
    }

    public void LoadCypherTxt(String FileName) {
        System.out.println(FileName + FileExtension);
        try {
            FileInputStream in = new FileInputStream(FileName + FileExtension);
            DataInputStream din = new DataInputStream(in);

            char[] mess = new char[10000];

            try {
                int i = 0;
                while (true) {
                    mess[i] = (char) din.readByte();
                    System.out.println("Received a |" + mess[i] + "|");
                    i++;
                }
            } catch (IOException e) {
                Encrypted.setText(String.valueOf(mess));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            Encrypted.setText("Can't Find File " + FileName + FileExtension);
        }
    }

    public void SaveMessage(String FileName) {
        System.out.println("Saved " + FileName + FileExtension);
        try {
            FileOutputStream out = new FileOutputStream(FileName + FileExtension);
            DataOutputStream dout = new DataOutputStream(out);

            String mess = new String(Message.getText());

            try {
                for (int i = 0; i < mess.length(); i++) {
                    dout.writeByte(mess.charAt(i));

                }
            } catch (IOException e) {
                Message.setText(String.valueOf(mess));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            messFN.setText("Can't Find File " + FileName + FileExtension);
        }
    }

    public void SaveCypherTxt(String FileName) {
        try {
            FileOutputStream out = new FileOutputStream(FileName + FileExtension);
            DataOutputStream dout = new DataOutputStream(out);

            String mess = new String(Encrypted.getText());

            try {
                for (int i = 0; i < mess.length(); i++) {
                    dout.writeByte(mess.charAt(i));
                }
            } catch (IOException e) {
                Message.setText(String.valueOf(mess));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            encFN.setText("Can't Find File " + FileName + FileExtension);
        }
    }

    public char EncryptChar(char c) {
        char ch;

        try {
            ch = lgrotor.charAt(smrotor.indexOf(c));
            ch = lgrotor.charAt(medrotor.indexOf(ch));

        } catch (Exception e) {
            System.out.println("Warning, character not in alphabet |" + c + "|");
            return c;
        }

        smrotor.turn();

        if (smrotor.turns() % 27 == 0) {
            medrotor.turn();
        }

        return ch;
    }

    public char DecryptChar(char c) {
        System.out.println("Decrypting " + c);
        char ch;

        try {
            ch = medrotor.charAt(lgrotor.indexOf(c));
            ch = smrotor.charAt(lgrotor.indexOf(ch));
        } catch (Exception e) {
            System.out.println("Warning, character not in alphabet |" + c + "|");
            return c;
        }

        smrotor.turn();

        if (smrotor.turns() % 27 == 0) {
            medrotor.turn();
        }

        return ch;
    }

    @Override
    public void paint(Graphics g) {

    }

    private class MouseKeeper extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
        }
    }

    private class WindowKeeper extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    abstract class ButtonAdapter extends Button implements ActionListener {

        public ButtonAdapter(String name) {
            super(name);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pressed();
        }

        public abstract void pressed();
    }
}
