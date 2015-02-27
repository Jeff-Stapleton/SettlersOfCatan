package client.view.join;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import shared.definitions.*;
import client.controller.join.IJoinGameController;
import client.view.base.*;

/**
 * Implementation for the select color view, which lets the user select the
 * desired color when they join a game
 */
@SuppressWarnings("serial")
public class SelectColorView extends OverlayView implements ISelectColorView {

	private final int LABEL_TEXT_SIZE = 32;
	private final int COLOR_BUTTON_TEXT_SIZE = 18;
	private final int DIALOG_BUTTON_TEXT_SIZE = 24;
	private final int BORDER_WIDTH = 10;

	private final String fontName = new JButton().getFont().getName();

	private JLabel lblTitle;
	private ColorButton btnRed;
	private ColorButton btnOrange;
	private ColorButton btnYellow;
	private ColorButton btnBlue;
	private ColorButton btnGreen;
	private ColorButton btnPurple;
	private ColorButton btnPuce;
	private ColorButton btnWhite;
	private ColorButton btnBrown;
	private JButton joinButton;
	private JButton cancelButton;

	private ColorButton selButton = null;
			
	public SelectColorView() {

		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory
				.createLineBorder(Color.black, BORDER_WIDTH));

		lblTitle = new JLabel("Join Game - Select Color");
		lblTitle.setFont(new java.awt.Font(fontName, 1, LABEL_TEXT_SIZE));
		
		btnRed = new ColorButton("Red", CatanColor.RED);
		btnRed.addActionListener(actionListener);

		btnOrange = new ColorButton("Orange", CatanColor.ORANGE);
		btnOrange.addActionListener(actionListener);

		btnYellow = new ColorButton("Yellow", CatanColor.YELLOW);
		btnYellow.addActionListener(actionListener);

		btnBlue = new ColorButton("Blue", CatanColor.BLUE);
		btnBlue.addActionListener(actionListener);

		btnGreen = new ColorButton("Green", CatanColor.GREEN);
		btnGreen.addActionListener(actionListener);

		btnPurple = new ColorButton("Purple", CatanColor.PURPLE);
		btnPurple.addActionListener(actionListener);

		btnPuce = new ColorButton("Puce", CatanColor.PUCE);
		btnPuce.addActionListener(actionListener);

		btnWhite = new ColorButton("White", CatanColor.WHITE);
		btnWhite.addActionListener(actionListener);

		btnBrown = new ColorButton("Brown", CatanColor.BROWN);
		btnBrown.addActionListener(actionListener);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		cancelButton.setFont(new java.awt.Font(fontName, 0, DIALOG_BUTTON_TEXT_SIZE));
		cancelButton.setOpaque(true);

		joinButton = new JButton("Join Game");
		joinButton.addActionListener(actionListener);
		joinButton.setFont(new java.awt.Font(fontName, 0, DIALOG_BUTTON_TEXT_SIZE));
		joinButton.setOpaque(true);
		joinButton.setEnabled(false);
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout(10, 10));
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
		labelPanel.add(Box.createHorizontalGlue());
		labelPanel.add(lblTitle);
		labelPanel.add(Box.createHorizontalGlue());
		
		rootPanel.add(labelPanel, BorderLayout.NORTH);
			
		JPanel colorsPanel = new JPanel();
		colorsPanel.setLayout(new GridBagLayout());
		
		addColorButton(colorsPanel, btnRed, 0, 0);
		addColorButton(colorsPanel, btnOrange, 1, 0);
		addColorButton(colorsPanel, btnYellow, 2, 0);
		addColorButton(colorsPanel, btnBlue, 0, 1);
		addColorButton(colorsPanel, btnGreen, 1, 1);
		addColorButton(colorsPanel, btnPurple, 2, 1);
		addColorButton(colorsPanel, btnPuce, 0, 2);
		addColorButton(colorsPanel, btnWhite, 1, 2);
		addColorButton(colorsPanel, btnBrown, 2, 2);
		
		rootPanel.add(colorsPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(joinButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
		rootPanel.add(buttonPanel, BorderLayout.SOUTH);
		rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(rootPanel);
	}
	
	private void addColorButton(JPanel buttonPanel, JButton button, int x, int y) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2, 4, 2, 4);
		
		buttonPanel.add(button, gbc);		
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == joinButton) {
				if (selButton != null) {
					getController().joinGame(getSelectedColor());
				}
			} else if (e.getSource() == cancelButton) {
				setSelectedColor(null);
				getController().cancelJoinGame();
			}
			else if (e.getSource() instanceof ColorButton)
			{
				setSelectedColor(((ColorButton)e.getSource()).getColor());
			}
		}
	};

	@Override
	public IJoinGameController getController()
	{
		return (IJoinGameController) super.getController();
	}

	@Override
	public void setColorEnabled(CatanColor color, boolean enable)
	{
		getButtonForColor(color).setEnabled(enable);
	}

	@Override
	public CatanColor getSelectedColor()
	{
		return selButton.getColor();
	}

	@Override
	public void setSelectedColor(CatanColor color)
	{
		if (selButton != null)
		{
			selButton.setSelected(false);
		}
		
		if (color == null)
		{
			selButton = null;
			joinButton.setEnabled(false);
		}
		else
		{
			selButton = getButtonForColor(color);
			selButton.setSelected(true);
			joinButton.setEnabled(true);
		}
	}

	@Override
	public void reset() {
		for (ColorButton color : new ColorButton[] {btnBlue, btnBrown, btnGreen,
												    btnOrange, btnPuce, btnPurple,
												    btnRed, btnWhite, btnYellow})
		{
			color.setEnabled(true);
		}
		setSelectedColor(null);
	}

	private ColorButton getButtonForColor(CatanColor color) {
		
		switch (color) {
		case BLUE:
			return btnBlue;
		case BROWN:
			return btnBrown;
		case GREEN:
			return btnGreen;
		case ORANGE:
			return btnOrange;
		case PUCE:
			return btnPuce;
		case PURPLE:
			return btnPurple;
		case RED:
			return btnRed;
		case WHITE:
			return btnWhite;
		case YELLOW:
			return btnYellow;
		default:
			assert false;
			return null;
		}
	}

	private class ColorButton extends JButton
	{
		private CatanColor color = null;
		private boolean isSelected = false;
		
		public ColorButton(String title, CatanColor color)
		{
			super(title);
			this.color = color;

			setSelected(false);
			setFont(new java.awt.Font(fontName, 1, COLOR_BUTTON_TEXT_SIZE));
			setContentAreaFilled(false);
			setOpaque(true);
		}
		
		@Override
		public void setEnabled(boolean isEnabled)
		{
			super.setEnabled(isEnabled);
			if (isEnabled)
			{
				setSelected(isSelected);
			}
			else
			{
				setBackground(Color.DARK_GRAY);
			}
		}
		
		public void setSelected(boolean isSelected)
		{
			this.isSelected = isSelected;
			//setEnabled(!isSelected);
			if (isSelected)
			{
				setBackground(color.getJavaColor());
			}
			else
			{
				setBackground(Color.LIGHT_GRAY);
			}
		}
		
		public CatanColor getColor()
		{
			return color;
		}
	}
}

