import java.util.Scanner;

/*

Simulator,
combines all of the search methods into 1 program,
Everything is launched from here.

*/
class Simulator {

  public static boolean alreadyHandledCommandLineInput; //a flag that insures command line input is only dealt with once
  public static Scanner lineReader; //used to get input from user on the console
  public static String response; //stores the response the user types in on the console

  public static SearchOne searchOne; //best first search using strategy 1
  public static SearchTwo SearchTwo; //best first search using strategy 2
  public static SearchThree SearchThree; //best first search using strategy 3
  public static SearchFour SearchFour; //best first search using strategy 4

  //Runs all of the search methods
  public static void attemptToRunAllSearchMethods(String onFileWithName) {

    searchOne = new SearchOne();
    SearchTwo = new SearchTwo();
    SearchThree = new SearchThree();
    SearchFour = new SearchFour();

    if (searchOne.Initialize(onFileWithName)) {

      searchOne.conductSearch();

    }
    else {

      return;

    }

    System.out.println();
    System.out.println("Search two");
    if (SearchTwo.Initialize(onFileWithName)) {

      SearchTwo.conductSearch();

    } else {

      return;

    }

    System.out.println();
    System.out.println("Search three");
    if (SearchThree.Initialize(onFileWithName)) {

      SearchThree.conductSearch();

    }
    else {

      return;

    }

    System.out.println();
    System.out.println("Search four");
    if (SearchFour.Initialize(onFileWithName)) {

      SearchFour.conductSearch();

    }
    else {

      return;

    }


  }

  public static void main(String[] args) {

    lineReader = new Scanner(System.in);
    alreadyHandledCommandLineInput = false;

    while (true) {

      if ((args.length > 0) && (alreadyHandledCommandLineInput == false)) {

        //Handle the command line input first:
        attemptToRunAllSearchMethods(args[0]);
        alreadyHandledCommandLineInput = true;

      }
      else if ((args.length == 0) || alreadyHandledCommandLineInput) {

        //see if user wants to try another file
        System.out.println("Enter the name of a file to read from, or enter e to exit: ");
        response = lineReader.nextLine();

        if (response.equals("e")) {

          System.out.println("Bye");
          break;

        }

        attemptToRunAllSearchMethods(response);

      }

    }

  }

}
