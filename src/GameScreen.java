import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class GameScreen extends JFrame{
    private Container contentPane=getContentPane();
    private JPanel fullPanel, topPanel, bottomPanel, gameGridPanel, topTextPanel;
    private JLabel rgbGameLabel;
    private JButton exitButton,resetButton,easyButton,hardButton;
    private Dimension gameGridPanelSize,buttonSize,topPanelSize;
    int row,column, red,green,blue;
    Color color,windowColor;
    String rgbTopText;


    public GameScreen(){
        fullPanel=new JPanel(new BorderLayout());
        fullPanel.setBorder(new EmptyBorder(50, 0, 50, 0));

//        setupTopPanel();
        setupGameGridPanel();
        setupBottomPanel();
        contentPane.add(fullPanel);
        setWindowBackgroundColor();
//        createMenu();
        setBounds(400, 200, 700, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }


    private void createAllPanels(){
        topPanelSize=new Dimension(500,50);
        topPanel=new JPanel(new BorderLayout());

        topTextPanel=new JPanel();


    }


    private void setupTopTextPanel(){


        rgbTopText="<html><h1>Which one is the color<br>" +
                "rgb(90,90,90)</h1></html>";
        rgbGameLabel =new JLabel(rgbTopText);


    }

    private void setupTopButtonPanel(){
        buttonSize=new Dimension(80, 40);

        resetButton=new JButton("Reset");
        easyButton=new JButton("Easy");
        hardButton=new JButton("Hard");


        resetButton.setPreferredSize(buttonSize);
        easyButton.setPreferredSize(buttonSize);
        hardButton.setPreferredSize(buttonSize);
        topPanel.setPreferredSize(topPanelSize);

    }

    private void setupTopPanel(){



        topPanel.add(rgbGameLabel);





        topPanel.add(resetButton);
        topPanel.add(easyButton);
        topPanel.add(hardButton);
        topPanel.add(rgbGameLabel);

        resetButton.addActionListener(actionEvent->resetEvent());

        fullPanel.add(topPanel,BorderLayout.NORTH);
    }

    private void setupGameGridPanel(){
//        ArrayList<E> <Color> lists = new ArrayList<>();

        gameGridPanelSize=new Dimension(390,270);
        buttonSize=new Dimension(160, 160);
        gameGridPanel=new JPanel(new GridBagLayout());
        gameGridPanel.setPreferredSize(new Dimension(gameGridPanelSize));

        GridBagConstraints c = new GridBagConstraints();
        row=2;
        column=3;
        for (int i = 0; i<row; i++) {
            c.gridy = i;
            for (int j = 0; j < column; j++) {
                c.gridx = j;
                JButton button = new JButton();
//                button.setFocusPainted(false);
//                button.setBorderPainted(false);
//                button.setContentAreaFilled(false);
                button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
                button.setPreferredSize(buttonSize);


                int red = (int)(Math.random() * 255 + 1);
                int green = (int)(Math.random() * 255 + 1);
                int blue = (int)(Math.random() * 255 + 1);

                color=new Color(red,green,blue);

//                lists.add(color);

                button.setBackground(color);
                button.setOpaque(true);

                c.insets= new Insets(7 , 7, 7, 7);        //ADDS SPACING BETWEEN GRIDS

                gameGridPanel.add(button, c);
            }
        }
        fullPanel.add(gameGridPanel,BorderLayout.CENTER);
    }

    private void setupBottomPanel(){
        bottomPanel= new JPanel();
        buttonSize=new Dimension(80, 40);

        exitButton=new JButton("Exit");
        exitButton.setPreferredSize(buttonSize);

        exitButton.addActionListener(actionEvent -> exitEvent());

        bottomPanel.add(exitButton);

        fullPanel.add(bottomPanel,BorderLayout.SOUTH);

    }


    private void resetEvent(){
        new GameScreen();
    }

    private void exitEvent(){
        System.exit(0);
    }

    private void setWindowBackgroundColor(){
        windowColor=new Color(90,90,90);

        fullPanel.setBackground(windowColor);
//        topPanel.setBackground(windowColor);
        gameGridPanel.setBackground(windowColor);
        bottomPanel.setBackground(windowColor);

    }

    public static void main(String[] args){
        new GameScreen();
    }


}



