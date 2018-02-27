/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import Enigma.Rotor.LargeRotor;
import Enigma.Rotor.MedRotor;
import Enigma.Rotor.SmallRotor;

public class Enigma {

    //final String FILE_EXTENSION = ".enigma";

    //public static Enigma enigma;

    //private final Insets ins;

    private SmallRotor smrotor = new SmallRotor();
    private MedRotor medrotor = new MedRotor();
    private LargeRotor lgrotor = new LargeRotor();

    protected String message;
    protected String encrypted;

    //protected String messFN;
    //protected String encFN;

    //protected String fNLabel = "Enter a FileName";
    //protected String fNLabel2 = "Enter a FileName";

    public Enigma(String message) {
        this.message = message;
        }
        
        /*
        setTitle("Enigma Simulator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        setLayout(new FlowLayout());

        add(message);
        Panel p = new Panel();
        p.setLayout(new GridLayout(5, 1));
        p.add(new ButtonAdapter("Encrypt") {
            @Override
            public void pressed() {
                EncryptEnigma();
            }
        });
        p.add(fNLabel);
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

        add(encrypted);
        Panel p2 = new Panel();
        p2.setLayout(new GridLayout(5, 1));
        p2.add(new ButtonAdapter("Decrypt") {
            @Override
            public void pressed() {
                DecryptEnigma();
            }
        });
        p2.add(fNLabel2);
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
    */
                
    public void EncryptEnigma() {
        String plain = message;
        plain = plain.toUpperCase();
        plain = plain.trim();
        char[] cypher = new char[10000];

        for (int i = 0; i < plain.length(); i++) {
            cypher[i] = EncryptChar(plain.charAt(i));

        }

        encrypted = String.copyValueOf(cypher);

        smrotor = new SmallRotor();
        medrotor = new MedRotor();
        lgrotor = new LargeRotor();

    }

    public void DecryptEnigma() {
        String cypher = encrypted;
        cypher = cypher.toUpperCase();
        cypher = cypher.trim();
        char[] plaintxt = new char[10000];

        for (int i = 0; i < cypher.length(); i++) {
            plaintxt[i] = DecryptChar(cypher.charAt(i));

        }

        message = String.copyValueOf(plaintxt);

        smrotor = new SmallRotor();
        medrotor = new MedRotor();
        lgrotor = new LargeRotor();
    }

    /*
    public void LoadMessage(String FileName) {
        System.out.println(FileName + FILE_EXTENSION);
        try {
            FileInputStream in = new FileInputStream(FileName + FILE_EXTENSION);
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
                message.setText(String.valueOf(mess));

            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            message.setText("Can't Find File " + FileName + FILE_EXTENSION);
        }
    }

    public void LoadCypherTxt(String FileName) {
        System.out.println(FileName + FILE_EXTENSION);
        try {
            FileInputStream in = new FileInputStream(FileName + FILE_EXTENSION);
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
                encrypted.setText(String.valueOf(mess));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            encrypted.setText("Can't Find File " + FileName + FILE_EXTENSION);
        }
    }

    public void SaveMessage(String FileName) {
        System.out.println("Saved " + FileName + FILE_EXTENSION);
        try {
            FileOutputStream out = new FileOutputStream(FileName + FILE_EXTENSION);
            DataOutputStream dout = new DataOutputStream(out);

            String mess = new String(message.getText());

            try {
                for (int i = 0; i < mess.length(); i++) {
                    dout.writeByte(mess.charAt(i));

                }
            } catch (IOException e) {
                message.setText(String.valueOf(mess));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            messFN.setText("Can't Find File " + FileName + FILE_EXTENSION);
        }
    }

    public void SaveCypherTxt(String FileName) {
        try {
            FileOutputStream out = new FileOutputStream(FileName + FILE_EXTENSION);
            DataOutputStream dout = new DataOutputStream(out);

            String mess = new String(encrypted.getText());

            try {
                for (int i = 0; i < mess.length(); i++) {
                    dout.writeByte(mess.charAt(i));
                }
            } catch (IOException e) {
                message.setText(String.valueOf(mess));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't Find File");
            encFN.setText("Can't Find File " + FileName + FILE_EXTENSION);
        }
    }
    */
    
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

    /*
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
    */
}
