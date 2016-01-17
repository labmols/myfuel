package myfuel.GUI;


import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Panel that enable to create custom radio button with an icon.
 */
@SuppressWarnings("serial")
public class XRadioButton extends JPanel {

	/**
	 * Radio Button.
	 */
    private JRadioButton radioButton;
    /**
     * Label that contains the icon.
     */
    private JLabel label;

    /**
     * Create new Custom Radio Button.
     */
    public XRadioButton() {
        setLayout(null);
        add(getRadioButton());
        add(getLabel());
    }

    /**
     * Create new Custom Radio Button with Icon and text.
     */
    public XRadioButton(Icon icon, String text) {
        this();
        setIcon(icon);
        setText(text);
    }

    protected JRadioButton getRadioButton() {
        if (radioButton == null) {
            radioButton = new JRadioButton();
            radioButton.setOpaque(false);
        }
        return radioButton;
    }

    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setLabelFor(getRadioButton());
        }
        return label;
    }

    /**
     * Add actionListener to the Radio Button.
     * @param listener - The ActionListener object.
     */
    public void addActionListener(ActionListener listener) {
        getRadioButton().addActionListener(listener);
    }

    /**
     * Remove actionListener to the Radio Button.
     * @param listener - The ActionListener object.
     */
    public void removeActionListener(ActionListener listener) {
        getRadioButton().removeActionListener(listener);
    }

    public void setText(String text) {
        getLabel().setText(text);
    }

    public String getText() {
        return getLabel().getText();
    }

    public void setIcon(Icon icon) {
        getLabel().setIcon(icon);
    }

    public Icon getIcon() {
        return getLabel().getIcon();
    }

}