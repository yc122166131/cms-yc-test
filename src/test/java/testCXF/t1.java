package testCXF;

import org.apache.cxf.jaxrs.client.WebClient;

import com.yc.test.entity.Student;
import com.yc.test.entity.StudentDto;



/*Ԫע��ļ��ֽ��ͣ�

@POST �C������ܴ���POST ����
@Path �C web�����URL·����ץȡURL Url <base_url>/bookservice/getbook/{name} , ����:<base_url>/bookservice/addbook
@Produces �C ָʾ��Ӧ��MIME���ͣ��ڰ������� application/xml �� application/json.
@Consumes �C ������������ѵ������MIME����*/


/**
 * ��һ�ַ�ʽ
 * ����� ͨ�� ע�� ��ʽ ����һ�� ����� (�Ƽ�)
 * @author yuanchen
 *
 */
public class t1 {

	//http://localhost:8281/testCXF2017/studentService/getStuInfo
	
	
	public static void main(String[] args) {
		
		String domainAddress = "http://localhost:8281/cms-yc-test/";
		String contentType= "application/json";
		// Service instance
		WebClient client = WebClient.create(domainAddress);
		/*StudentDto stu = client.path("services/studentService/getStuInfo/333")
				.accept(contentType).get(StudentDto.class);*/
		Student stu2 = new Student();
		stu2.setName("����");
		StudentDto stu = client.path("services/studentService/getStuInfoByPost")
				.accept(contentType).post(stu2,StudentDto.class);
		System.out.println(stu);
		
	}
	
	/*public static void main(String[] args) {
		try {
			String domainAddress = "http://localhost:8281/testCXF2017/";
			String contentType= "application/json";
			// Service instance
			WebClient client = WebClient.create(domainAddress);
			Category category = client.path("categoryservice/category/001")
					.accept(contentType).get(Category.class);
			System.out.println("Category details from REST service.");
			System.out.println("Category Name " + category.getCategoryName());
			System.out.println("Category Id " + category.getCategoryId());
			System.out.println("Book details for Category "
					+ category.getCategoryId() + " from REST service");
			String bookString = "categoryservice/category/"
					+ category.getCategoryId() + "/books";
			WebClient clientBook = WebClient.create(domainAddress);
			Category categoryBooks = clientBook.path(bookString)
					.accept(contentType).get(Category.class);
			Iterator<Book> iterator = categoryBooks.getBooks().iterator();
			while (iterator.hasNext()) {
				Book book = iterator.next();
				System.out.println("Book Name " + book.getBookName());
				System.out.println("Book ISBN " + book.getBookISBNnumber());
				System.out.println("Book ID " + book.getBookId());
				System.out.println("Book Author " + book.getAuthor());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
