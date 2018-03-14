package PROJECTS;

import javax.swing.*;
import java.awt.*;

/**
 * Project name: HomeWork
 * Created by pavel on 08.10.2017.
 */
public class Window extends JFrame {


    public static void main(String[] args) {
        Window gui = new Window() ;
    }



    Container contentPane = getContentPane() ;

    JPanel panel = new JPanel();
    JPanel grid = new JPanel( new GridLayout( 1 , 4 ) ) ;


    JTextField txt1 = new JTextField( 30 ) ;
    JTextField txt2 = new JTextField( "Текст по умолчанию" , 30 ) ;

    JTextArea txtArea = new JTextArea( 7 , 28 ) ;
    JScrollPane pane = new JScrollPane( txtArea ) ;


    JCheckBox chk1 = new JCheckBox( "Пеперони" ) ;
    JCheckBox chk2 = new JCheckBox( "Грибная" ) ;
    JCheckBox chk3 = new JCheckBox( "С ветчиной" ) ;
    JCheckBox chk4 = new JCheckBox( "Томатная" ) ;

    String[] styles = { "В глубокой форме" , "Для гурманов" , "Тонкая" } ;

    JComboBox<String> box1 = new JComboBox<String>( styles ) ;


    JRadioButton rad1 = new JRadioButton( "Красное" , true ) ;
    JRadioButton rad2 = new JRadioButton( "Розовое" ) ;
    JRadioButton rad3 = new JRadioButton( "Белое" ) ;

    ButtonGroup wines = new ButtonGroup() ;


    JLabel lbl1 = new JLabel( "Пользовательский задний фон" ) ;

    Color customColor = new Color(74, 255, 93) ;





    public Window() {
        super( "Program" ) ;
        setSize( 450 , 450 ) ;
        setDefaultCloseOperation( EXIT_ON_CLOSE ) ;

        contentPane.add( "North" , panel ) ;
        contentPane.add("Center" , grid ) ;

        panel.add(new JButton( "Нажми меня"));



        txtArea.setLineWrap( true ) ;
        txtArea.setWrapStyleWord( true ) ;

        pane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ) ;

        panel.add( txt1 ) ;
        panel.add( txt2 ) ;

        txtArea.setOpaque(true);
        txtArea.setBackground(customColor);
        panel.add( pane ) ;

        grid.add( chk1 ) ;
        grid.add( chk2 ) ;
        grid.add( chk3 ) ;
        grid.add( chk4 ) ;

        box1.setSelectedIndex( 0 ) ;
        panel.add( box1 ) ;


        wines.add( rad1 ) ;
        wines.add( rad2 ) ;
        wines.add( rad3 ) ;

        panel.add( rad1 ) ;
        panel.add( rad2 ) ;
        panel.add( rad3 ) ;

        lbl1.setOpaque( true ) ;
        lbl1.setBackground( customColor) ;

        panel.add( lbl1 ) ;

        setVisible( true ) ;
    }
}
