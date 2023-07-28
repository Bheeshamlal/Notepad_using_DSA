import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener , KeyListener {

    JMenuBar menuBar;
    JMenu file, edit, mOptions; //more options (mOptions)
    JMenuItem create, open, save, exist, cut, copy, paste, selectAll, undo ,redo, find, replace;
    public JTextArea textArea;
    JScrollPane scrollPane;
    String text;
    Stack1 stack = new Stack1();
    
    public Notepad()
    {

        //JFrame Settings
        super("Notepad App");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(super.EXIT_ON_CLOSE);
        setBackground(Color.white);
        String path = "src/icons/Notepad-Bloc-notes-icon.png";
        ImageIcon icon = new ImageIcon(path);
        setIconImage(icon.getImage());


        //file Menu Settings
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        //file menuItems Settings
        //create
        create = new JMenuItem("New");
        create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        create.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        create.setBackground(Color.white);
        create.addActionListener(this); //adding ActionListener to menuItem
        file.add(create); //adding to menu file

        //open
        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        open.setBackground(Color.white);
        open.addActionListener(this); //adding ActionListener to menuItem
        file.add(open); //adding to menu file


        //save
        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        save.setBackground(Color.white);
        save.addActionListener(this); //adding ActionListener to menuItem
        file.add(save); //adding to menu file

        //exist
        exist = new JMenuItem("Exit         ");
        exist.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE,0));
        exist.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        exist.setBackground(Color.white);
        exist.addActionListener(this); //adding ActionListener to menuItem
        file.add(exist); //adding to menu file

        //Edit Menu Settings
        edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        edit.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        //edit menuItems Settings
        //cut
        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        cut.setBackground(Color.white);
        cut.addActionListener(this); //adding ActionListener to menuItem
        edit.add(cut); //adding to menu edit

        //copy
        copy = new JMenuItem("Copy   ");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        copy.setBackground(Color.white);
        copy.addActionListener(this); //adding ActionListener to menuItem
        edit.add(copy); //adding to menu edit

        //paste
        paste = new JMenuItem("Paste   ");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        paste.setBackground(Color.white);
        paste.addActionListener(this); //adding ActionListener to menuItem
        edit.add(paste); //adding to menu edit

        //selectAll
        selectAll = new JMenuItem("Select All         ");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectAll.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        selectAll.setBackground(Color.white);
        selectAll.addActionListener(this); //adding ActionListener to menuItem
        edit.add(selectAll); //adding to menu edit

        //mOptions Menu Settings
        mOptions = new JMenu("More Options");
        mOptions.setMnemonic(KeyEvent.VK_M);
        mOptions.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        //mOptions menuItems Settings
        //undo
        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        undo.setBackground(Color.white);
        undo.addActionListener(this); //adding ActionListener to menuItem
        mOptions.add(undo); //adding to menu mOptions

        //redo
        redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        redo.setBackground(Color.white);
        redo.addActionListener(this); //adding ActionListener to menuItem
        mOptions.add(redo); //adding to menu mOptions

        //find
        find = new JMenuItem("Find ");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, ActionEvent.CTRL_MASK));
        find.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        find.setBackground(Color.white);
        find.addActionListener(this); //adding ActionListener to menuItem
        mOptions.add(find); //adding to menu mOptions

        //replace
        replace = new JMenuItem("Replace         ");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        replace.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        replace.setBackground(Color.white);
        replace.addActionListener(this); //adding ActionListener to menuItem
        mOptions.add(replace); //adding to menu mOptions

        //menuBar Settings
        menuBar = new JMenuBar();
        //adding menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(mOptions);
        menuBar.setBackground(new Color(211,211,211));
        setJMenuBar(menuBar);

        // textArea Settings
        textArea = new JTextArea();
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //scrollPane Settings
        scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane); //adding to frame

        textArea.addKeyListener(this);        
    }
    public void keyReleased(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_DELETE) || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
            
            stack.push(textArea.getText());
            stack.display();
        }
        else if(e.getKeyChar()>=32&&e.getKeyChar()<=126)
        {
            stack.push(textArea.getText());
            stack.display();
        }
    }
    public void keyTyped(KeyEvent e) {
        
    }

    public static void main(String agrs[])
    {
        new Notepad().setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("New"))
        {
            textArea.setText("");
        }

        else if(e.getActionCommand().equals("Open"))
        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Only Text Files", ".txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(fileFilter);

            int option = fileChooser.showOpenDialog(null);
            if(option != fileChooser.APPROVE_OPTION)
            {
                return;
            }

            else
            {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.read(reader, null);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }

        else if(e.getActionCommand().equals("Save"))
        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Only Text Files", ".txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(fileFilter);

            int option = fileChooser.showSaveDialog(null);
            if(option != fileChooser.APPROVE_OPTION)
            {
                return;
            }

            else
            {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();

                if(!fileName.contains(".txt"))
                {
                    fileName += ".txt";
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(writer);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        else if(e.getActionCommand().equals("Exit         "))
        {
            System.exit(0);
        }

        else if(e.getActionCommand().equals("Cut"))
        {
            textArea.cut();
        }

        else if(e.getActionCommand().equals("Copy   "))
        {
            textArea.copy();
        }

        else if(e.getActionCommand().equals("Paste   "))
        {
            textArea.paste();
        }

        else if(e.getActionCommand().equals("Select All         "))
        {
            textArea.selectAll();
        }
        else if(e.getActionCommand().equals("Undo"))
        {
            if(stack.isEmpty()){
            textArea.setText(stack.pop());
            stack.display();
            }

        }

        else if(e.getActionCommand().equals("Redo"))
        {
            if(stack.isEmpty()){
                textArea.setText(stack.pop1());
                stack.display();
                }
        }

        else if(e.getActionCommand().equals("Find "))
        {
            

            final String inputValue = JOptionPane.showInputDialog("Find What?");
            final int l1 = textArea.getText().indexOf(inputValue);
            final int l2 = inputValue.length();

            if (l1 == -1) {
                JOptionPane.showMessageDialog(null, "Search Value Not Found");
            }else{
                textArea.select(l1, l2+l1);
            }
            
        }

        else if(e.getActionCommand().equals("Replace         "))
        {
            JFrame frame = new JFrame("Replace Function");
            JButton btn = new JButton("Replace");
            JTextField jt1,jt2;
            JLabel jl1;
        
            jt1 = new JTextField(8);
            jl1 = new JLabel("With");
            jt2 = new JTextField(8);


            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                  String from = jt1.getText();
                  int start = textArea.getText().indexOf(from);
                  if (start >= 0 && from.length() > 0)
                    textArea.replaceRange(jt2.getText(), start, start
                        + from.length());
                    
                }
              });
          

            frame.add(btn);
            frame.add(jt1);
            frame.add(jl1);
            frame.add(jt2);


            frame.setSize(400,60);
            frame.setVisible(true);
            frame.setLayout(new FlowLayout());
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
            frame.setLocation(x, y);

        }
        
        
    }
}
