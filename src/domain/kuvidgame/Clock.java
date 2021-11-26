package domain.kuvidgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;




public class Clock implements ActionListener {

	private Timer timer = new Timer(1000, this);
	private boolean isRunning = false;
	private int second;
	private int minute;

	public Clock(int min, int sec) {
		second = sec;
		minute = min;
	}

	public void start() {
		if (isRunning) {
			System.out.println("Clock is already running.");
		} else {
			isRunning = true;
			timer.start();
		}
	}

	public void stop() {
		if (isRunning) {
			isRunning = false;
			timer.stop();
		} else {
			System.out.println("Clock is already stopped.");
		}
	}

	public String getTime() {
		String minuteString = Integer.toString(minute);
		String secondString = Integer.toString(second);

		if (minute < 10 && minute >= 0) {
			minuteString = "0" + Integer.toString(minute);
		}
		if (second < 10 && second >= 0) {
			secondString = "0" + Integer.toString(second);
		}

		String time = minuteString + ":" + secondString;

		return time;
	}

	public boolean isZero() {
		boolean timeIsUp = false;
		if (minute == 0 && second == 0)
			timeIsUp = true;
		return timeIsUp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		second--;
		if (second == -1) {
			minute--;
			second = 59;
		}

		if (isZero())
			System.out.println("time is up.");

	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	public int getRemainingSeconds() {
		
		return (minute*60) + second;
	}
}
