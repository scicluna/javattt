import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            button_panel.add(buttons[i]);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]) {
                if (buttons[i].getText()==""){
                    buttons[i].setForeground(new Color(255,0,0));
                    if(player1_turn){
                        buttons[i].setText("X");
                        textfield.setText("O turn");
                    } else {
                        buttons[i].setText("O");
                        textfield.setText("X turn");
                    }
                    check(player1_turn);
                    player1_turn = !player1_turn;
                }
            }
        }
    }

    public void firstTurn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(random.nextInt(2)==0){
            player1_turn=true;
            textfield.setText("X turn");
        } else {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }

    public void check(Boolean player1_turn){
        String player = player1_turn ? "X" : "O";
        if (buttons[0].getText()==player && buttons[1].getText()==player && buttons[2].getText()==player){
            wins(player, 0, 1, 2);
        }
        if (buttons[3].getText()==player && buttons[4].getText()==player && buttons[5].getText()==player){
            wins(player, 3, 4, 5);
        }
        if (buttons[6].getText()==player && buttons[7].getText()==player && buttons[8].getText()==player){
            wins(player, 6, 7, 8);
        }
        if (buttons[0].getText()==player && buttons[3].getText()==player && buttons[6].getText()==player){
            wins(player, 0, 3, 6);
        }
        if (buttons[1].getText()==player && buttons[4].getText()==player && buttons[7].getText()==player){
            wins(player, 1, 4, 7);
        }
        if (buttons[2].getText()==player && buttons[5].getText()==player && buttons[8].getText()==player){
            wins(player, 2, 5, 8);
        }
        if (buttons[0].getText()==player && buttons[4].getText()==player && buttons[8].getText()==player){
            wins(player, 0, 4, 8);
        }
        if (buttons[2].getText()==player && buttons[4].getText()==player && buttons[6].getText()==player){
            wins(player, 2, 4, 6);
        }
    }

    public void wins(String player, int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        String victory = String.format("%s wins!", player);
        textfield.setText(victory);
    }

}
