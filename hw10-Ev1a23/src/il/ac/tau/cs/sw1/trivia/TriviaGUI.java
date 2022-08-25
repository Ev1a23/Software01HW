package il.ac.tau.cs.sw1.trivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import il.ac.tau.cs.sw1.bufferedIO.IBufferedReader;
import il.ac.tau.cs.sw1.bufferedIO.MyBufferReader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	private ArrayList<ArrayList<String>> questions;

	private int score = 0;

	private int index = 1;

	public int errors = 0;

	public int skip_cnt = 0;

	public int fifty = 0;

	public int number = 0;
	boolean gameover;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		questions = new ArrayList<>();
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent selectionEvent) {
				String path = GUIUtils.getFilePathFromFileDialog(shell);
				filePathField.setText(path);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent selectionEvent) {

			}
		});

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent selectionEvent) {
				lastAnswer = "";
				score = 0;
				index = 1;
				errors = 0;
				skip_cnt = 0;
				fifty = 0;
				number = 0;
				scoreLabel.setText(String.valueOf(score));
				gameover = false;
				ArrayList<String> tst = new ArrayList<>();
				String inputFileName2 = filePathField.getText();
				FileReader fReader2 = null;
				try {
					fReader2 = new FileReader(new File(inputFileName2));
				} catch (FileNotFoundException e) {
					throw new RuntimeException(e);
				}
				BufferedReader bR2 = new BufferedReader(fReader2);
				boolean flag = true;
				while(flag)
				{
					String out = "";
					try {
						out = bR2.readLine();
					}
					catch (Exception e)
					{

					}

					if(out == null)
					{
						flag = false;
					}
					else
					{
						tst.add(out);
					}
				}
				int ind = 0;
				for(String str: tst)
				{
					ArrayList<String> lst = new ArrayList<>();
					int i = 0;
					String s = "";
					while(i<str.length())
					{
						char c;
						int cnt = 0;
						while (cnt <5 && i < str.length())
						{
							if(str.charAt(i) == '?' && cnt == 0)
							{
								lst.add(cnt,s+"?");
								cnt+=1;
								i+=2;
								s = "";
							}
							else if(str.charAt(i) != '\t')
							{
								s += str.charAt(i);
								i+=1;
							}
							else
							{
								lst.add(cnt,s);
								s ="";
								i +=1;
								cnt+=1;
							}
						}
						lst.add(4,s);
						questions.add(lst);
						ind +=1;
					}
				}
				removedups();
				Collections.shuffle(questions);
				ind = questions.size();
				List<String> tmp = new ArrayList<>();
				tmp.add(questions.get(0).get(1));
				tmp.add(questions.get(0).get(2));
				tmp.add(questions.get(0).get(3));
				tmp.add(questions.get(0).get(4));
				updateQuestionPanel(questions.get(0).get(0), tmp);
			}

			public void removedups()
			{
				for (int i = 0; i< questions.size(); i++)
				{
					for(int j = i+1; j<questions.size(); j++)
					{
						int cnt = 0;
						for(int k = 0; k< questions.get(0).size(); k++)
						{

							if(!questions.get(i).get(k).equals(questions.get(j).get(k)))
							{
								break;
							}
							else
							{
								cnt+=1;
							}
						}
						if(cnt ==5)
						{
							questions.remove(questions.get(i));
						}
					}
				}
			}



			@Override
			public void widgetDefaultSelected(SelectionEvent selectionEvent) {

			}
		});
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		// Save current list of answers.
		List<String> currentAnswers = answers;
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));
		String correct = answers.get(0);

		// create the answer buttons
		answerButtons.clear();
		ArrayList<Integer> rnd = new ArrayList<Integer>();
		rnd.add(0);rnd.add(1);rnd.add(2);rnd.add(3);
		Collections.shuffle(rnd);
		for (int i = 0; i < 4; i++) {

			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(rnd.get(i)));
			if(answerButton.getText() == correct)
			{
				number = i;
			}
			answerButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent selectionEvent) {
					if (!gameover) {
						if (answerButton.getText() == correct) {
							errors = 0;
							score += 3;
							lastAnswer = "Correct! ";
							scoreLabel.setText(String.valueOf(score));
							instructionLabel.setText(lastAnswer);
							if (index < questions.size()) {
								reload_next();
							} else {
								GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after " + index + " questions");
								gameover = true;
								instructionLabel.setText(lastAnswer + "Answer the following question:");

							}
						} else {
							score -= 2;
							errors += 1;
							lastAnswer = "Wrong... ";
							scoreLabel.setText(String.valueOf(score));
							instructionLabel.setText(lastAnswer);
							if (index < questions.size() && errors < MAX_ERRORS) {
								reload_next();
							} else {
								instructionLabel.setText(lastAnswer + "Answer the following question:");
								GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after " + index + " questions");
								gameover = true;
							}
						}
					}
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent selectionEvent) {

				}
			});
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			
			answerButtons.add(answerButton);
		}
		//continue to play


		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		passButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent selectionEvent) {
				if (!gameover) {
					if (skip_cnt == 0 || score > 0) {
						if (skip_cnt != 0) {
							score -= 1;
							scoreLabel.setText(String.valueOf(score));
						}
						skip_cnt += 1;
						reload_next();
					}
				}
				else {
						if (skip_cnt != 0) {
							score -= 1;

						}
						skip_cnt+=1;
						GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " + score + " after " + index + " questions");

					}
				}


			@Override
			public void widgetDefaultSelected(SelectionEvent selectionEvent) {

			}
		});
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		score_check();
		fiftyFiftyButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent selectionEvent) {
				if(fifty == 0 || score >0)
				{
					if(fifty != 0)
					{
						score -=1;
						scoreLabel.setText(String.valueOf(score));
					}
					fifty +=1;
					fiftyFiftyButton.setEnabled(false);
					Random rnd = new Random();
					int r = rnd.nextInt(4);
					if(r == number)
					{
						if (number != 0) {
							r -= 1;
						}
						else
						{
							r = 3;
						}
					}
					for(int i = 0; i< 4; i++)
					{
						if(i == r || i == number)
						{
							continue;
						}
						else
						{
							answerButtons.get(i).setEnabled(false);
						}
					}


				}
			}


			@Override
			public void widgetDefaultSelected(SelectionEvent selectionEvent) {

			}
		});


		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}
	public void reload_next()
	{
		score_check();
		List<String> tmp = new ArrayList<>();
		tmp.add(questions.get(index).get(1));
		tmp.add(questions.get(index).get(2));
		tmp.add(questions.get(index).get(3));
		tmp.add(questions.get(index).get(4));
		updateQuestionPanel(questions.get(index).get(0), tmp);
		index += 1;
	}

	public void score_check()
	{
		if(score<=0 &&skip_cnt >0)
		{
			passButton.setEnabled(false);
		}
		else
		{
			passButton.setEnabled(true);
		}

		if(score<=0 && fifty >0)
		{
			fiftyFiftyButton.setEnabled(false);
		}
		else
		{
			fiftyFiftyButton.setEnabled(true);
		}
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
}
