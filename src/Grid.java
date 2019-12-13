import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grid extends JDialog {

    public class MyComparator implements java.util.Comparator<String> {

        private int referenceLength;

        public MyComparator(String reference) {
            super();
            this.referenceLength = reference.length();
        }

        public int compare(String s1, String s2) {
            int dist1 = Math.abs(s1.length() - referenceLength);
            int dist2 = Math.abs(s2.length() - referenceLength);

            return dist1 - dist2;
        }
    }

    int coloumn;
    int row;

    JTextField[][] fields;
    String[][] fieldsStr;
    JMenuBar menuBar;
    JMenu create;
    JMenuItem deneme;
    ArrayList<Tuple> tuples;
    ArrayList<Tuple> tuplesSorted;


    public Grid(JFrame jFrame,int x, int y){
        super(jFrame,true);
        tuples = new ArrayList<>();
        tuplesSorted=new ArrayList<>();
        coloumn=y;
        row =x;

        menuBar = new JMenuBar();
        add(menuBar);
        create= new JMenu("Create");
        menuBar.add(create);
        deneme=new JMenuItem("generate");
        create.add(deneme);
        setJMenuBar(menuBar);

        deneme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //burda asil olustur sonucu
                getClues();
                findSolutionHorizontal();
                findSolutionVertical();
                setDependecies();
                setGrid();
                fillInOneBlocks();
            }
        });

        createBoard();
        fillItWithBlack();

    }
    public void createBoard(){
        fields = new JTextField[row][coloumn];
        getContentPane().setLayout(new java.awt.GridLayout(coloumn, row, 0, 0));
        Container c = getContentPane();
        for (int i = 0; i < coloumn; i++) {
            for (int j=0;j<row;j++) {
                fields[i][j] = new JTextField();
                c.add(fields[i][j]);
            }
        }
        pack();

    }
    public void fillItWithBlack(){
        Random rand= new Random();
        if (row>=5 && row<7){
            int rand_int1 = (int)(Math.random()*((3-1)+1))+1; // 1 ile 2 arasi
            if (rand_int1==0)
                 rand_int1 = (int)(Math.random()*((3-1)+1))+1;
            int counter =0;
            for (int i = 0; i < coloumn; i++) {
                while (counter<rand_int1) {
                    int randWhereYouWantItToBeBlack = rand.nextInt(row);
                    fields[i][randWhereYouWantItToBeBlack].setBackground(Color.black);
                    fields[i][randWhereYouWantItToBeBlack].setText("null");
                    fields[i][randWhereYouWantItToBeBlack].setForeground(Color.black);
                    counter++;
                }
                counter=0;
            }
        }else if (row>=7 ){
            int rand_int1 = (int)(Math.random()*((5-3)+1))+3; // 1 ile 2 arasi bosluk olur
            if (rand_int1==0)
                rand_int1 =(int)(Math.random()*((5-3)+1))+3;
            int counter =0;
            for (int i = 0; i < coloumn; i++) {
                while (counter<rand_int1) {
                    int randWhereYouWantItToBeBlack = rand.nextInt(row);
                    fields[i][randWhereYouWantItToBeBlack].setText("null");
                    fields[i][randWhereYouWantItToBeBlack].setForeground(Color.black);
                    fields[i][randWhereYouWantItToBeBlack].setBackground(Color.black);
                    counter++;
                }
                counter=0;
            }
        }

    }
    public void getClues(){
        fieldsStr=new String[row][coloumn];
        for (int i = 0; i < coloumn; i++) {
            for (int j = 0; j < row; j++) {
               if (fields[i][j].getText().length()>0)
                    fieldsStr[i][j] = fields[i][j].getText();
               else
                   fieldsStr[i][j] =".";
            }
        }
    }
    public void findSolutionHorizontal(){

        for (int i = 0; i < coloumn; i++) {
            int length = 0;
            String temp = "";
            for (int j = 0; j < row; j++) {
                if(fieldsStr[i][j].equals("null")){
                    if(length > 1){
                        tuples.add(new Tuple(i, j-temp.length(), temp, true));
                    }
                    temp = "";
                    length = 0;
                    continue;
                }
                temp += fieldsStr[i][j];
                length += 1;
            }
            if(length > 1){
                tuples.add(new Tuple(i, row-temp.length(), temp, true));
            }
        }
    }
    public void findSolutionVertical(){
        for (int i = 0; i < coloumn; i++) {
            int length = 0;
            String temp = "";
            for (int j = 0; j < row; j++) {
                if(fieldsStr[j][i].equals("null")){
                    if(length > 1){
                        tuples.add(new Tuple(j-temp.length(), i, temp, false));
                    }
                    temp = "";
                    length = 0;
                    continue;
                }
                temp += fieldsStr[j][i];
                length += 1;
            }
            if(length > 1){
                tuples.add(new Tuple(row-temp.length(), i, temp, false));
            }
        }

    }
    public void updateTuple(){
        int counter=0;
        for (int i = 0; i < coloumn; i++) {
            int length = 0;
            String temp = "";
            for (int j = 0; j < row; j++) {
                if (fieldsStr[i][j].equals("null")) {
                    if (length > 1) {
                        tuples.get(counter).setWord(temp);
                        counter++;
                    }
                    temp = "";
                    length = 0;
                    continue;
                }
                temp += fieldsStr[i][j];
                length += 1;
            }
            if(length > 1){
                tuples.get(counter).setWord(temp);
                counter++;
            }
        }//horizontal

        for (int i = 0; i < coloumn; i++) {
            int length = 0;
            String temp = "";
            for (int j = 0; j < row; j++) {
                if (fieldsStr[j][i].equals("null")) {
                    if (length > 1) {
                        tuples.get(counter).setWord(temp);
                        counter++;
                    }
                    temp = "";
                    length = 0;
                    continue;
                }
                temp += fieldsStr[j][i];
                length += 1;
            }
            if(length > 1){
                tuples.get(counter).setWord(temp);
                counter++;
            }
        }//vertical

       /* for (int i=0;i<tuples.size();i++){
            System.out.println("X:" + tuples.get(i).getX()+ " Y:"+ tuples.get(i).getY()+ " Word: " + tuples.get(i).getWord());
        }
        System.out.println();
        System.out.println("denemee\n");*/
       // sortByLengthOfWord();
    }

    private void placeIntoGrid(Tuple tuple, String word){
        int deltaX = 0, deltaY = 0;
        if(tuple.getItHorizantol()){
            deltaY = 1;
        }else{
            deltaX = 1;
        }
        int startX = tuple.getX();
        int startY = tuple.getY();
        for(int i = 0; i < word.length(); ++i){
            fieldsStr[startX+i*deltaX][startY+i*deltaY] = ""+word.charAt(i);
        }
        for (int i = 0; i < coloumn; i++) {
            for (int j = 0; j < row; j++) {
                fields[i][j].setText(fieldsStr[i][j]);
                Font font = new Font("Courier", Font.BOLD,20);
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(font);
            }
        }
    }

    public void setGrid(){
        Tree tree= new Tree();
        /*for (int i=0;i<tuples.size();i++){
            if (tuples.get(i).getItHorizantol()){
                tree.possibleOutcomes(tuples.get(i).getWord());
                if(!tree.possible.isEmpty()) {
                    placeIntoGrid(tuples.get(i), tree.possible.get(0));
                }//else in de programi durdurabilir
            }else {
                tree.possibleOutcomes(tuples.get(i).getWord());
                if(!tree.possible.isEmpty()) {
                    placeIntoGrid(tuples.get(i), tree.possible.get(0));
                }//else in de programi durdurabilir
            }
        }*/

        //int indexOfLargestWord=getMax(tupleWords);
        //ilk en uzunu olani set et
       /*tree.possibleOutcomes(tuples.get(indexOfLargestWord).getWord());
        if(!tree.possible.isEmpty()) {
            placeIntoGrid(tuples.get(indexOfLargestWord), tree.possible.get(random.nextInt(tree.possible.size())));
        }*/

        Random random = new Random();
        sortByLengthOfWord();
       for (int i=0;i<tuplesSorted.size();i++) {
           tree.possibleOutcomes(tuplesSorted.get(i).getWord());
            if (tuplesSorted.get(i).getItHorizantol()) {

                if (!tree.possible.isEmpty()) {
                    placeIntoGrid(tuplesSorted.get(i), tree.possible.get(random.nextInt(tree.possible.size())));
                    updateTuple();
                    //sortByLengthOfWord();
                    //continue;
                }else
                    System.out.println("HATALI");

            } else {
                if (!tree.possible.isEmpty()) {
                    placeIntoGrid(tuplesSorted.get(i), tree.possible.get(random.nextInt(tree.possible.size())));
                   updateTuple();
                    //sortByLengthOfWord();
                   // continue;
                }else
                    System.out.println("HATALI");
            }
          // updateTuple();
           //sortByLengthOfWord();
          // System.out.println(tuplesSorted.get(i).getWord());
        }




    }

    public void sortByLengthOfWord(){
        for (int i=0;i<tuples.size();i++){
            tuplesSorted.add(tuples.get(i));
        }
        Collections.shuffle(tuplesSorted);
        for(int i=0;i<tuplesSorted.size();i++) {
            for(int j=i+1;j<tuplesSorted.size();j++) {
                if(tuplesSorted.get(i).getWord().length()>tuplesSorted.get(j).getWord().length()) {
                    Tuple temp=tuplesSorted.get(i);
                    tuplesSorted.set(i,tuplesSorted.get(j));
                    tuplesSorted.set(j,temp);
                }
            }
        }
        Collections.reverse(tuplesSorted);

        for (int i=0;i<tuplesSorted.size();i++){
            System.out.println("X:" + tuplesSorted.get(i).getX()+ " Y:"+ tuplesSorted.get(i).getY()+ " Word: " + tuplesSorted.get(i).getWord() + " Is it horzontal: " +tuplesSorted.get(i).getItHorizantol() );
        }
    }
   /* public int getMax(ArrayList<String> list){
        int max = Integer.MIN_VALUE;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).length() > max){
                max = i;
            }
        }
        return max;
    }*/

    public void fillInOneBlocks(){
        Tree tree= new Tree();
        Random random= new Random();
        for (int i=0;i<row;i++){
            for (int j=0;j<row;j++){
                if (fieldsStr[i][j].equals(".")){
                    tree.possibleOutcomes(".");
                    fieldsStr[i][j]=tree.possible.get(random.nextInt(tree.possible.size()));
                    fields[i][j].setText(fieldsStr[i][j]);
                }
            }
        }
    }

    public void setDependecies(){
        for(int i=0;i<tuples.size();i++){
            for (int j=0;j<tuples.size();j++){
                if (j==i)
                    continue;
                tuples.get(i).addDependency(tuples.get(j));
            }
        }
    }

}
