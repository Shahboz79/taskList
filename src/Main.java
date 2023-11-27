import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        System.out.println("Click on one of the numbers below");
        Scanner scanner=new Scanner(System.in);
        System.out.println("1-Create");
        System.out.println("2-Active Task List");
        System.out.println("3-Finished Task List");
        System.out.println("4-Update (by id)");
        System.out.println("5-Delete (by id)");
        System.out.println("6-Mark as done (by id)");
        System.out.print("Select the desired number: ");
        int number=scanner.nextInt();

        switch (number) {

            case 1:
                Scanner scanner1=new Scanner(System.in);
                System.out.print("Enter title: ");
                String title=scanner1.nextLine();

                Scanner scanner2=new Scanner(System.in);
                System.out.print("Enter content: ");
                String content=scanner2.nextLine();

                TaskService taskService=new TaskService();
                System.out.println(taskService.create(new TaskDTO(title, content)));
                break;

            case 2:
                System.out.println(new TaskService().getActiveTaskList());
                break;

            case 3:
                System.out.println(new TaskService().getFinishedTaskList());
                break;

            case 4:
                Scanner upId= new Scanner(System.in);
                System.out.print("Enter Task Id: ");
                Integer id=upId.nextInt();

                Scanner upTitle=new Scanner(System.in);
                System.out.print("Enter title: ");
                String uTitle=upTitle.nextLine();

                Scanner upContent=new Scanner(System.in);
                System.out.print("Enter content: ");
                String uContent=upContent.nextLine();

                TaskService utaskService=new TaskService();
                System.out.println(utaskService.update(new TaskDTO(id, uTitle, uContent)));
                break;
            case 5:
                Scanner dId= new Scanner(System.in);
                System.out.print("Enter Delete Task Id: ");
                Integer delId=dId.nextInt();

                System.out.println(new TaskService().delete(delId));
                break;
            case 6:
                Scanner fId= new Scanner(System.in);
                System.out.print("Enter Task Id: ");
                Integer finId=fId.nextInt();

                String markAsDone = new TaskService().markAsDone(finId);
                if (markAsDone==null){
                    System.out.println("You have entered an invalid id. Start again");
                    menu();
                }else {
                System.out.println(markAsDone);}
                break;
            default:
                menu();
                break;

        }

    }
}