import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class GameScreen extends JFrame{
    private Container contentPane=getContentPane();
    private JPanel fullPanel, topPanel, bottomPanel, gameGridPanel, topTextPanel, topButtonPanel;
    private JLabel topGameLabel;
    private JButton exitButton,resetButton,easyButton,hardButton;
    private Dimension gameGridPanelSize,buttonSize,topPanelSize;
    int row,column, red,green,blue, topRed,topGreen,topBlue;
    Color color, topTextColor, windowColor;
    String topText;
    ArrayList<ArrayList<Integer>> colorArrayList;

    public GameScreen(){
        fullPanel=new JPanel(new BorderLayout());

        createAllPanels();
        setupTopButtonPanel();
        setupTopPanel();
        setupGameGridPanel(2,3);
        setupTopTextPanel();
        setupBottomPanel();
        contentPane.add(fullPanel);
        setWindowBackgroundColor();
//        createMenu();
        setBounds(300, 100, 800, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }


    private void createAllPanels(){
        topPanel=new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));

        topTextPanel=new JPanel();
        topTextPanel.setBorder(new EmptyBorder(0,70,0,50));

        topButtonPanel=new JPanel();

    }


    private void setupTopTextPanel(){

        getRandomColor();

        topText ="<html><h1>Which one is the color<br>" +
                "rgb("+ topRed + ", " + topGreen + ", " +topBlue+")</h1></html>";
        topGameLabel =new JLabel(topText);

        topTextPanel.add(topGameLabel);


    }

    private void getRandomColor(){
        Random rand = new Random();
        int index=rand.nextInt(colorArrayList.size());
        ArrayList item = colorArrayList.get(index);
        topRed = (int) item.get(0);
        topGreen = (int) item.get(1);
        topBlue= (int) item.get(2);
        topTextColor=new Color(topRed,topGreen,topBlue);
    }


    private void setupTopButtonPanel(){
        buttonSize=new Dimension(80, 40);

        resetButton=new JButton("Reset");
        easyButton=new JButton("Easy");
        hardButton=new JButton("Hard");


        resetButton.setPreferredSize(buttonSize);
        easyButton.setPreferredSize(buttonSize);
        hardButton.setPreferredSize(buttonSize);

        topButtonPanel.add(resetButton);
        topButtonPanel.add(easyButton);
        topButtonPanel.add(hardButton);

        resetButton.addActionListener(actionEvent->resetEvent());

    }

    private void setupTopPanel(){

        topPanel.add(topTextPanel);
        topPanel.add(topButtonPanel);

        fullPanel.add(topPanel,BorderLayout.NORTH);
    }

    private void setupGameGridPanel(int row, int column){
        buttonSize=new Dimension(160, 160);

        gameGridPanel=new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        row=2;
//        column=3;
        int colorNum = row*column;

        //creates empty inner Arraylist of size colorNum for each rgb color
        createColorArrayList(colorNum);

        for (int i = 0, b=0; i<row; b=i+3, i++) {
            c.gridy = i;
            for (int j = 0; j < column; j++) {
                c.gridx = j;
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
                button.setPreferredSize(buttonSize);

                //sets random Color for red, green and blue
                randomColor();

                //adds rgb colors to inner Arraylist for each row
//                for (int colorPosition=0 ; colorPosition<3; colorPosition++){
                    colorArrayList.get(b+j).add(red);
                    colorArrayList.get(b+j).add(green);
                    colorArrayList.get(b+j).add(blue);
//                }

                button.setBackground(color);
                button.setOpaque(true);

                button.addActionListener(e -> {
                    if (((JButton)e.getSource()).getBackground().equals(topTextColor)){
//                        ((JButton) e.getSource()).removeAll();
                        topTextPanel.removeAll();
                        topTextPanel.revalidate();
                        topTextPanel.repaint();
                        topText="<html><h1 style='color: #FFFFFF'>CORRECT Choice!</h1></html>";
                        topGameLabel =new JLabel(topText);
                        topTextPanel.add(topGameLabel);

                        topTextPanel.setBorder(new EmptyBorder(40,0,40,0));
                        topTextPanel.setBackground(topTextColor);
                    }
//                    System.out.println(topTextColor);
//                    System.out.println(((JButton)e.getSource()).getBackground());
                });



                c.insets= new Insets(7 , 7, 7, 7);        //ADDS SPACING BETWEEN GRIDS

                gameGridPanel.add(button, c);
            }
        }
        System.out.println(colorArrayList);
        fullPanel.add(gameGridPanel,BorderLayout.CENTER);
    }

    private void createColorArrayList(int colorNum){
        colorArrayList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<colorNum; i++){
            colorArrayList.add(new ArrayList<>());
        }
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

    private void randomColor(){

        red = (int)(Math.random() * 255 + 1);
        green = (int)(Math.random() * 255 + 1);
        blue = (int)(Math.random() * 255 + 1);

        color=new Color(red,green,blue);
    }


    private void resetEvent(){
//        this.dispose();
//        new GameScreen();
        fullPanel.removeAll();
        fullPanel.revalidate();
        fullPanel.repaint();
        createAllPanels();
        setupTopButtonPanel();
        setupTopPanel();
        setupGameGridPanel(2,3);
        setupTopTextPanel();
        setupBottomPanel();
        contentPane.add(fullPanel);
        setWindowBackgroundColor();

    }

    private void exitEvent(){
        System.exit(0);
    }

    private void setWindowBackgroundColor(){
//        windowColor=new Color(90,90,90);

        fullPanel.setBackground(new Color(80,38,49));
        topPanel.setBackground(new Color(120,120,10));
        gameGridPanel.setBackground(new Color(10,120,120));
        bottomPanel.setBackground(new Color(120,10,120));

    }

    public static void main(String[] args){
        new GameScreen();
    }


}



