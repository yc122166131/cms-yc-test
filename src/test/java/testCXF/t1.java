package testCXF;

import org.apache.cxf.jaxrs.client.WebClient;

import com.yc.test.entity.Student;
import com.yc.test.entity.StudentDto;



/*元注解的几种解释：

@POST –服务接受处理POST 请求
@Path – web服务的URL路径，抓取URL Url <base_url>/bookservice/getbook/{name} , 增加:<base_url>/bookservice/addbook
@Produces – 指示响应的MIME类型，在案例中是 application/xml 和 application/json.
@Consumes – 这个服务能消费的请求的MIME类型*/


/**
 * 第一种方式
 * 服务端 通过 注解 形式 返回一个 大对象 (推荐)
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
		stu2.setName("张三");
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
