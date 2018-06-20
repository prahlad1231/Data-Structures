/*
	Driver Program for linked list
*/

import java.util.Scanner;

class SLinkList {
	public static void main(String[] args) {
		int choice, data;
		LinkedList list = new LinkedList();
		Scanner sc = new Scanner(System.in);
		System.out.println("Link List Test \n");
		do {
			System.out.println("1. Insert  2. Display  3. Delete  4. Exit");
			choice = sc.nextInt();
			switch(choice) {
				case 1: 
					System.out.println("1. Insert at end  2. Insert at position p");
					System.out.print("Enter choice: ");
					int insertChoice = sc.nextInt();
					switch(insertChoice) {
						case 1:
							System.out.print("Enter data: ");
							data = sc.nextInt();
							list.insert(data);
							break;
						case 2: 
							System.out.print("Enter data and position separated by whitespace: ");
							data = sc.nextInt();
							int pos = sc.nextInt();
							list.insert(data, pos);
							break;
						default: break;
					}
					break;
					
				case 2:
					list.displayList();
					System.out.println("Size: "+list.getSize());
					break;
					
				case 3:
					System.out.println("1. Delete at end  2. Delete at position p");
					System.out.print("Enter choice: ");
					int deleteChoice = sc.nextInt();
					switch(deleteChoice) {
						case 1: 
							list.delete();
							break;
						case 2: 
							System.out.print("Enter position: ");
							int pos = sc.nextInt();
							list.delete(pos);
							break;
						default: break;
					}
					break;
					
				case 4:
					sc.close();
					System.exit(0);
					break;
					
				default: break;
			}
		} while(choice != 4);
		sc.close();
	}
}