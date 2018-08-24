package L18_Oct21;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * @author Garima Chhikara
 * @email garima.chhikara@codingblocks.com
 * @date 21-Oct-2017
 */

public class TypingTutor extends JFrame implements ActionListener {

	private JLabel lblTimer;
	private JLabel lblScore;
	private JLabel lblWord;
	private JTextField txtWord;
	private JButton btnStart;
	private JButton btnStop;

	private Timer timer;
	private int timeRemaining;
	private int score;
	private boolean running;

	private String[] words;

	public TypingTutor(String[] words) {

		this.words = words;
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		Font font = new Font("Comic Sans MS", 1, 150);

		lblTimer = new JLabel("Timer:");
		lblTimer.setFont(font);
		super.add(lblTimer);

		lblScore = new JLabel("Score:");
		lblScore.setFont(font);
		super.add(lblScore);

		lblWord = new JLabel();
		lblWord.setFont(font);
		// lblWord.setForeground(Color.YELLOW);
		// lblWord.setBackground(Color.BLACK);
		// lblWord.setOpaque(true);

		super.add(lblWord);

		txtWord = new JTextField();
		txtWord.setFont(font);
		super.add(txtWord);

		btnStart = new JButton("Start");
		btnStart.setFont(font);
		// btnStart.setForeground(Color.RED);
		// btnStart.setBackground(Color.BLACK);
		btnStart.addActionListener(this);
		super.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setFont(font);
		btnStop.addActionListener(this);
		super.add(btnStop);

		super.setTitle("Typing Tutor");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);

		setUpGame();
	}

	private void setUpGame() {
		timer = new Timer(1000, this);
		timeRemaining = 5;
		score = 0;
		running = false;

		lblTimer.setText("Timer:" + timeRemaining);
		lblScore.setText("Score: " + score);
		lblWord.setText("");
		txtWord.requestFocus();
		txtWord.setText("");		
		btnStart.setText("Start");
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			handleStart();
		} else if (e.getSource() == btnStop) {
			handleStop();
		} else if (e.getSource() == timer) {
			handleTimer();
		}
	}

	private void handleTimer() {

		timeRemaining--;

		String correctWord = lblWord.getText();
		String userWord = txtWord.getText();

		if (correctWord.equals(userWord) && correctWord.length() > 0) {
			score++;
		}
		lblScore.setText("Score: " + score);

		if (timeRemaining < 0) {
			handleStop();
			return;
		}
		lblTimer.setText("Time: " + timeRemaining);

		int ridx = (int) (Math.random() * words.length);
		lblWord.setText(words[ridx]);
		txtWord.setText("");
		txtWord.requestFocus();
	}

	private void handleStop() {
		// JOptionPane.showMessageDialog(this, "In stop");

		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Replay ? ") ;
		
		if(choice == JOptionPane.YES_OPTION){
			setUpGame();
		}else if(choice == JOptionPane.NO_OPTION){
			super.dispose();
		}else{
			if(timeRemaining < 0){
				setUpGame();
			}else{
				timer.stop();
			}
		}
	}

	private void handleStart() {
		// JOptionPane.showMessageDialog(this, "In start");

		if (running == false) {
			timer.start();
			running = true;

			btnStart.setText("Pause");
			btnStop.setEnabled(true);

		} else {
			timer.stop();
			running = false;

			btnStart.setText("Start");
		}

	}

}
