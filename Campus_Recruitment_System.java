import java.io.*;
import java.util.*;

class Operator
{
	public static void clean() {
		try {
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.println("\033c");
			}
		} catch (Exception exception) {
			//  Handle exception.
		}
    }//清屏
	String filename=null;
	String directory=null;
	public Operator(String fname,String dir){
		filename=new String(fname);
		directory=new String(dir);
	}
	public void add(ArrayList<String> infolist){
		try {
			Operator.clean();
			System.out.println(filename);
			File file = new File(directory);
			Scanner scan =new Scanner(System.in);
			FileOutputStream fos = new FileOutputStream(file,true);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"GBK");
			BufferedWriter bw = new BufferedWriter(osw);
			String info=scan.next();
			infolist.add(info);
			bw.write(info);
			bw.newLine();
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
		}catch (Exception e) {
		} 
	}//新增
	public void view(){
		try{
			Operator.clean();
			System.out.println(filename);
			File file = new File(directory);
			FileInputStream fis=new FileInputStream(file);
			InputStreamReader isr=new InputStreamReader(fis,"GBK");
			BufferedReader br=new BufferedReader(isr);
			String line = br.readLine();
			  while (line!=null) { 
				 System.out.println(line);
				 line = br.readLine();
			  }
	   }catch(Exception e){
	   }
	}//查看
	public void modify(int a,ArrayList<String> infolist){
		try{
			Operator.clean();
			System.out.println(filename);
			System.out.println("请输入信息：");
			InputStreamReader isr=new InputStreamReader(System.in,"GBK");
			BufferedReader br=new BufferedReader(isr);
			String line=br.readLine();
			infolist.set(a,line);
			br.close();
			isr.close();
			File file = new File(directory);
			
			FileOutputStream fos=new FileOutputStream(file);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"GBK");
			BufferedWriter bw=new BufferedWriter(osw);

			for(int i=0;i<infolist.size();i++){
				String str =infolist.get(i);
				bw.write(str);
				bw.newLine();
			}
			bw.close();
			osw.close();
			fos.close();
		 }catch(Exception e){
		 }
	}//修改
	public void delete(int a,ArrayList<String> infolist){
		 try{
			 Operator.clean();
			 System.out.println(filename);
			 infolist.remove(a);
			 File file = new File(directory);
			 FileOutputStream fos=new FileOutputStream(file);
			 OutputStreamWriter osw=new OutputStreamWriter(fos,"GBK");
			 BufferedWriter bw=new BufferedWriter(osw);
			 for(int i=0;i<infolist.size();i++){
				 String str =infolist.get(i);
				 bw.write(str);
				 bw.newLine();
			 }
			 bw.close();
			 osw.close();
			 fos.close();
		 }catch(Exception e){
		 }
	}//删除

}//操作类


class Door{
	String Directory=null;
	public Door(String Dir){
		Directory=new String(Dir);
	}
	public void register(String users){
		try{
		InputStreamReader isr=new InputStreamReader(System.in,"GBK");
		BufferedReader sbr=new BufferedReader(isr);
		File file=new File(Directory+"\\"+users+"\\"+users+"_list.txt");
		try{
			if(!file.exists()){
				file.createNewFile();
			}
		}catch(Exception E){
		}
		Scanner sreg=new Scanner(new FileInputStream(file));
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		int num;
		if(!sreg.hasNext()){
			num=1;
		}
		else{
			num=sreg.nextInt()+1;
		}
		ArrayList<String> list=new ArrayList<String>();
		while(br.ready()){
			list.add(br.readLine());
		}
		if(list.size()!=0)
		list.set(0,String.valueOf(num));
		else
			list.add(String.valueOf(num));
		list.add(String.valueOf(num-1));
		System.out.println("请输入您的用户名：");
		String name=sbr.readLine();

		String secretkey=null;
		do{
			System.out.println("请输入您的密码：");
			 secretkey=sbr.readLine();
			System.out.println("请再次输入您的密码：");
			if(secretkey.equals(sbr.readLine())){
				System.out.println("注册成功！");
				Thread.sleep(500);
				Operator.clean();
				break;
			}
			else{
				System.out.println("密码不一致，请重新输入！");
				Thread.sleep(500);
				Operator.clean();
			}
		}while(true);
		(new File(Directory+"\\"+users+"\\"+name)).mkdirs();
		list.add(name);
		list.add(secretkey);
		list.add(Directory+"\\"+users+"\\"+name);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
		for(int i=0;i<list.size();i++){
			bw.write(list.get(i));
			bw.newLine();
		}
		bw.close();
		br.close();
		}catch(Exception e){
		}
	}//注册
	public String login(String users){
		String name=null;
		try{
			ArrayList<String> list=new ArrayList<String>();
		File file=new File(Directory+"\\"+users+"\\"+users+"_list.txt");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		int num=Integer.parseInt(br.readLine());
		while(br.ready()){
			list.add(br.readLine());
		}
		BufferedReader sbr=new BufferedReader(new InputStreamReader(System.in,"GBK"));
		boolean match=false;
		
		while(true){
			System.out.println("请输入您的用户名：");
			name=sbr.readLine();
			System.out.println("请输入您的密码：");
			String secretkey=sbr.readLine();
			for(int i=0;i<num;i++){
				if(list.get(i*4+1).equals(name)&&list.get(i*4+2).equals(secretkey)){
					match=true;
					break;
				}
			}
			if(match){
				System.out.println("登录成功！");
				Thread.sleep(500);
				Operator.clean();
				break;
			}
			else{
				System.out.println("您输入的信息有误，请重新输入！");
				Thread.sleep(500);
				Operator.clean();
			}
		}
		}catch(Exception e){
		}
		return name;
	}//登录
}//注册登录类




class Student extends Door{
	public Student(String Dir){
		super(Dir);
	}
	public void submit(String directory,String postdir,String stname){
		String indinfo="individual_information";
		Scanner scan=new Scanner(System.in);
		  System.out.println("请输入您要投递的简历编号：");
		  String bg="Biography"+scan.nextInt();
		  try{
			  File f=new File(directory+"\\"+bg+".txt");
			  ArrayList<String> bglist=new ArrayList<String>();
			  BufferedReader bgbr=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
			  while(bgbr.ready()){
				  bglist.add(bgbr.readLine());
			  }
			  bgbr.close();
			  bglist.set(0,"审核中");
			  BufferedWriter bgbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  for(int i=0;i<bglist.size();i++){
				  bgbw.write(bglist.get(i));
				  bgbw.newLine();
			  }
			  bgbw.close();
		  }catch(Exception E){
		  }//更改简历上应聘状态
		  try{
			  File f=new File(directory+"\\resume_delivery.txt");
			  try{
				  if(!f.exists()){
					  f.createNewFile();
				  }
			  }catch(Exception E){
			  }
			  ArrayList<String> rdlist=new ArrayList<String>();
			  BufferedReader rdbr=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
			  while(rdbr.ready()){
				  rdlist.add(rdbr.readLine());
			  }
			  rdbr.close();
			  rdlist.add(bg);
			  rdlist.add(postdir+"\\recruitment_information.txt");
			  BufferedWriter rdbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  for(int i=0;i<rdlist.size();i++){
				  rdbw.write(rdlist.get(i));
				  rdbw.newLine();
			  }
			  rdbw.close();
		  }catch(Exception E){
		  }//记录投递信息
		  try{
			  File f=new File(postdir+"\\circumstance.txt");
			  ArrayList<String> cirlist=new ArrayList<String>();
			  BufferedReader cirbr=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
			  while(cirbr.ready()){
				  cirlist.add(cirbr.readLine());
			  }
			  cirbr.close();
			  int apnum=Integer.parseInt(cirlist.get(0));
			  cirlist.set(0,String.valueOf(apnum+1));
			  cirlist.add(String.valueOf(apnum));
			  cirlist.add(stname);
			  cirlist.add(directory+"\\"+indinfo+".txt");
			  cirlist.add(directory+"\\"+bg+".txt");
			  cirlist.add("审核中");
			  BufferedWriter cirbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  for(int i=0;i<cirlist.size();i++){
				  cirbw.write(cirlist.get(i));
				  cirbw.newLine();
			  }
			  cirbw.close();
		  }catch(Exception E){
		  }//在用人单位记录应聘信息
		  System.out.println("已投！");
		  try{
			  Thread.sleep(1000);
			  Operator.clean();
		  }catch(Exception E){
		  }
	}//投递简历
	public void operate(String stname){
		String directory=Directory+"\\students\\"+stname;
		String indinfo="individual_information";
      System.out.println("您可使用以下功能：");
	  System.out.println("如要进行个人信息的新增、查看、修改、删除等 请输入0");
	  System.out.println("如要进行个人简历的新增、查看、修改、删除等 请输入1");
	  System.out.println("如要进行查看用人单位发布的招聘信息         请输入2");
	  System.out.println("如要进行查看已投递的应聘信息及审核状态     请输入3");
	  Scanner scan=new Scanner(System.in);
	  int first=scan.nextInt();
	  if(first==0){
		  ArrayList<String> infolist=new ArrayList<String>();
		try{
			File file = new File(directory+"\\"+indinfo+".txt");
			if (!file.exists()) {
				try {
						file.createNewFile();
					} catch (Exception e) {
					}
				} 
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String line = br.readLine();
			
			while (line != null) {
				infolist.add(line);
				line = br.readLine();
		}
            br.close();
			Thread.sleep(500);
			Operator.clean();
		}catch(Exception e){
		}//将个人信息存入数组
          System.out.println("新增请输入0");
          System.out.println("查看请输入1");
          System.out.println("修改请输入2");
          System.out.println("删除请输入3");
		  int second=scan.nextInt();
		  Operator o=new Operator(indinfo,directory+"\\"+indinfo+".txt");
		  if(second==0){
			  while(true){
				System.out.println("请输入您要新增的信息：");
				o.add(infolist);
				System.out.println("如需继续新增信息请输入0 退出请输入1");
				if(scan.nextInt()==1)
				break;
				System.out.flush();
				Operator.clean();
			  }
		  }
		  else if(second==1){
			  o.view();
	      }
	      else if(second==2){
			  while(true){
					System.out.println("请问您要修改第几条信息：");
					o.modify(scan.nextInt()-1,infolist);
					System.out.println("如需继续修改请输入0 退出请输入1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
	      }
	     else if(second==3){
			 System.out.println("请问您要删除第几条信息：");
			 o.delete(scan.nextInt()-1,infolist);
	      }
	  }//个人信息
	  else if(first==1){
		  Operator.clean();
          System.out.println("请问您要操作第几份简历：");
			String bg="Biography"+scan.nextInt();
		  ArrayList<String> infolist=new ArrayList<String>();
		try{
			File file = new File(directory+"\\"+bg+".txt");
			if (!file.exists()) {
				try {
						file.createNewFile();
						BufferedWriter bW=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
						bW.write("未投递");
						bW.newLine();
						bW.close();
					} catch (Exception e) {
					}
				} 
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String line = br.readLine();
			while (line != null) {
				infolist.add(line);
				line = br.readLine();
		}
            br.close();
		}catch(Exception e){
		}//将简历存入数组
		  Operator.clean();
          System.out.println("新增请输入0");
          System.out.println("查看请输入1");
          System.out.println("修改请输入2");
          System.out.println("删除请输入3");
		  int second=scan.nextInt();
		  Operator o=new Operator(bg,directory+"\\"+bg+".txt");
		  if(second==0){
				while(true){
					System.out.println("请输入您要新增的信息：");
					o.add(infolist);
					System.out.println("如需继续新增信息请输入0 退出请输入1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
				}
		  }
		  else if(second==1){
			  o.view();
	      }
	      else if(second==2){
			  while(true){
					System.out.println("请问您要修改第几条信息：");
					o.modify(scan.nextInt(),infolist);
					System.out.println("如需继续修改请输入0 退出请输入1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
	      }
	     else if(second==3){
			 System.out.println("请问您要删除第几条信息：");
			 o.delete(scan.nextInt(),infolist);
	      }
	  }//个人简历
	  else if(first==2){
		  System.out.println("如要按用人单位查看 请输入0");
		  System.out.println("如要按岗位查看     请输入1");
		  System.out.println("如要按薪酬查看     请输入2");
		  int second=scan.nextInt();
		  if(second==0){
			  System.out.println("请输入您想要应聘的用人单位名称：");
			  scan.nextLine();
			  String elname=scan.nextLine();
			  try{
				  File file=new File(Directory+"\\recruitment_list.txt");
				  ArrayList<String> reclist=new ArrayList<String>();
				  BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				  while(br.ready()){
					  reclist.add(br.readLine());
				  }
				  boolean found=false;
				  for(int i=0;i<reclist.size();i+=5){
					  if(reclist.get(i).equals(elname)){
						  found=true;
						  System.out.println("用人单位名称："+reclist.get(i));
						  System.out.println("岗位："+reclist.get(i+1));
						  System.out.println("月薪："+reclist.get(i+2));
						  System.out.println("要求"+reclist.get(i+3));
						  System.out.println("是否投递？ 若是请输入0 若否请输入1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+4),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("该用人单位尚未发布招聘信息");
				  br.close();
			  }catch(Exception E){
			  }
		  }//按用人单位查看
		  else if(second==1){
			  System.out.println("请输入您想要应聘的岗位名称：");
			  scan.nextLine();
			  String postname=scan.nextLine();
			  try{
				  File file=new File(Directory+"\\recruitment_list.txt");
				  ArrayList<String> reclist=new ArrayList<String>();
				  BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				  while(br.ready()){
					  reclist.add(br.readLine());
				  }
				  boolean found=false;
				  for(int i=1;i<reclist.size();i+=5){
					  if(reclist.get(i).equals(postname)){
						  found=true;
						  System.out.println("用人单位名称："+reclist.get(i-1));
						  System.out.println("岗位："+reclist.get(i));
						  System.out.println("月薪："+reclist.get(i+1));
						  System.out.println("要求"+reclist.get(i+2));
						  System.out.println("是否投递？ 若是请输入0 若否请输入1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+3),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("尚未有用人单位发布该岗位的招聘信息");
				  br.close();
			  }catch(Exception E){
			  }
		  }//按岗位查看
		  else if(second==2){
			  System.out.println("请输入您想要的月薪下限（输入数字）：");
			  int minsalary=scan.nextInt();
			  System.out.println("请输入您想要的月薪上限（输入数字）：");
			  int maxsalary=scan.nextInt();
			  try{
				  File file=new File(Directory+"\\recruitment_list.txt");
				  ArrayList<String> reclist=new ArrayList<String>();
				  BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				  while(br.ready()){
					  reclist.add(br.readLine());
				  }
				  boolean found=false;
				  int salary;
				  for(int i=2;i<reclist.size();i+=5){
					  salary=Integer.parseInt(reclist.get(i));
					  if(salary<=maxsalary&&salary>=minsalary){
						  found=true;
						  System.out.println("用人单位名称："+reclist.get(i-2));
						  System.out.println("岗位："+reclist.get(i-1));
						  System.out.println("月薪："+reclist.get(i));
						  System.out.println("要求"+reclist.get(i+1));
						  System.out.println("是否投递？ 若是请输入0 若否请输入1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+2),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("月薪在该范围内的招聘信息尚未发布");
				  br.close();
			  }catch(Exception E){
			  }
		  }//按薪酬查看
	  }//查看招聘信息并投递简历
	  else if(first==3){
		  try{
			  File f=new File(directory+"\\resume_delivery.txt");
			  BufferedReader rebr=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
			  ArrayList<String> sublist=new ArrayList<String>();
			  while(rebr.ready()){
				  sublist.add(rebr.readLine());
			  }
			  rebr.close();
			  String recdir;
			  for(int i=0;i<sublist.size();i+=2){
				  System.out.println(sublist.get(i));
				  recdir=sublist.get(i+1);
				  try{
					  File file=new File(directory+"\\"+sublist.get(i)+".txt");
					  BufferedReader cirbr=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
					  System.out.println("该招聘目前的审核状态为："+cirbr.readLine());
					  cirbr.close();
				  }catch(Exception E){
				  }
				  try{
					  File file=new File(recdir);
					  ArrayList<String> reclist=new ArrayList<String>();
					  BufferedReader recbr=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
					  System.out.println("该招聘信息如下：");
					  while(recbr.ready()){
						  reclist.add(recbr.readLine());
					  }
					  recbr.close();
					  for(int j=0;j<reclist.size();j++){
						  System.out.println(reclist.get(j));
					  }
				  }catch(Exception E){
					  System.out.println("该招聘已招满");
				  }
			  }
		  }catch(Exception E){
		  }
	  }//查看已投递的应聘信息及审核状态
	}
}//学生类

class Employer extends Door{
	public Employer(String Dir){
		super(Dir);
	}
	public void operate(String elname){
		String directory=Directory+"\\employers\\"+elname;
		String elinfo="employer_information";//用人单位信息
		String recinfo="recruitment_information";//招聘信息
		Scanner scan=new Scanner(System.in);
		System.out.println();
		System.out.println("您可进行以下操作：");
		System.out.println("如要进行用人单位信息的新增、查看、修改、删除等 请输入0");
		System.out.println("如要发布招聘信息                               请输入1");
		System.out.println("如要管理招聘信息                               请输入2");
		System.out.println("如要审核招聘信息                               请输入3");
		int first=scan.nextInt();
		scan.nextLine();
		if(first==0){
			ArrayList<String> infolist=new ArrayList<String>();
			try{
				File file = new File(directory+"\\"+elinfo+".txt");
				if (!file.exists()) {
					try {
							file.createNewFile();
						} catch (Exception e) {
						}
					} 
				BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				String line = br.readLine();
			
				while (line != null) {
					infolist.add(line);
					line = br.readLine();
			    }
			    br.close();
				Operator.clean();
			}catch(Exception e){
			}//将个人信息存入数组
			  System.out.println();
			 System.out.println("新增请输入0");
			 System.out.println("查看请输入1");
			 System.out.println("修改请输入2");
			System.out.println("删除请输入3");
			int second=scan.nextInt();
			 Operator o=new Operator(elinfo,directory+"\\"+elinfo+".txt");
			 if(second==0){
			  while(true){
				System.out.println("请输入您要新增的信息：");
				o.add(infolist);
				System.out.println("如需继续新增信息请输入0 退出请输入1");
				if(scan.nextInt()==1)
				break;
				System.out.flush();
				Operator.clean();
			  }
			}
			  else if(second==1){
				  o.view();
			  }
			 else if(second==2){
			  while(true){
					System.out.println("请问您要修改第几条信息：");
					o.modify(scan.nextInt()-1,infolist);
					System.out.println("如需继续修改请输入0 退出请输入1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
			 }
			else if(second==3){
				 System.out.println("请问您要删除第几条信息：");
				 o.delete(scan.nextInt()-1,infolist);
			 }
		}//用人单位信息
		else if(first==1){
			Operator.clean();
		  System.out.println("请输入您要发布的招聘岗位名：");
		  String post=scan.nextLine();
		  (new File(directory+"\\post_"+post)).mkdirs();
		  try{
			  File f=new File(directory+"\\post_"+post+"\\circumstance.txt");
			  if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (Exception e) {
				}
			  }
			  BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  bw.write("0");
			  bw.newLine();
			  bw.close();
		  }catch(Exception e){
		  }
		  File file=new File(directory+"\\post_"+post+"\\"+recinfo+".txt");
		  try{
			  if(!file.exists())
				file.createNewFile();
		  }catch(Exception e){
		  }
		  try{
			  BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
			  bw.write("岗位："+post);
			  bw.newLine();
			  System.out.println("请输入月薪（输入数字）：");
			  String wage=scan.nextLine();
			  bw.write("月薪："+wage);
			  bw.newLine();
			  System.out.println("请输入应聘要求：");
			  String request=scan.nextLine();
			  bw.write("要求："+request);
			  bw.newLine();
			  bw.close();
			  //招聘信息
			  try{
				  File f=new File(Directory+"\\recruitment_list.txt");
				  BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
				  ArrayList<String> reclist=new ArrayList<String>();
				  while(br.ready()){
					  reclist.add(br.readLine());
				  }
				  br.close();
				  BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
				  reclist.add(elname);
				  reclist.add(post);
				  reclist.add(wage);
				  reclist.add(request);
				  reclist.add(directory+"\\post_"+post);//岗位所属用人单位信息文件
				  for(int i=0;i<reclist.size();i++){
					  bw1.write(reclist.get(i));
					  bw1.newLine();
				  }
				  bw1.close();
			  }catch(Exception e){
			  }//招聘信息发布
		  }catch(Exception e){
		  }
		  System.out.println("已发布！");
		}//发布招聘信息
		else if(first==2){
			try{
				Operator.clean();
				File file=new File(Directory+"\\recruitment_list.txt");
				BufferedReader recbr=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				ArrayList<String> reclist=new ArrayList<String>();
				while(recbr.ready()){
					reclist.add(recbr.readLine());
				}
				recbr.close();
				for(int i=0;i<reclist.size();i+=5){
					if((reclist.get(i)).equals(elname)){
						try{
							File cirf=new File(reclist.get(i+4)+"\\circumstance.txt");
							BufferedReader cirbr=new BufferedReader(new InputStreamReader(new FileInputStream(cirf),"GBK"));
							ArrayList<String> cirlist=new ArrayList<String>();
							while(cirbr.ready()){
								cirlist.add(cirbr.readLine());
							}
							cirbr.close();
							System.out.println("该招聘信息内容如下：");
							System.out.println("用人单位名："+reclist.get(i));
							System.out.println("岗位名："+reclist.get(i+1));
							System.out.println("月薪："+reclist.get(i+2));
							System.out.println("要求："+reclist.get(i+3));
							System.out.println();
							if((cirlist.get(0)).equals("0")){
								System.out.println("该招聘尚未收到投递，是否修改信息？如是请输入0 如否请输入1");
								if(scan.nextInt()==0){
									try{
										File reif=new File(reclist.get(i+4)+"\\"+recinfo+".txt");
										BufferedReader reibr=new BufferedReader(new InputStreamReader(new FileInputStream(reif),"GBK"));
										ArrayList<String> reilist=new ArrayList<String>();
										while(reibr.ready()){
											reilist.add(reibr.readLine());
										}
										reibr.close();
										System.out.println("是否修改岗位？如是请输入0 如否请输入1");
										if(scan.nextInt()==0){
											System.out.println("请输入修改后的岗位：");
											scan.nextLine();
											String postname=scan.nextLine();
											reilist.set(0,"岗位："+postname);
											reclist.set(i+1,postname);
										}
										System.out.println("是否修改月薪？如是请输入0 如否请输入1");
										if(scan.nextInt()==0){
											System.out.println("请输入修改后的月薪：");
											scan.nextLine();
											String salary=scan.nextLine();
											reilist.set(1,"月薪："+salary);
											reclist.set(i+2,salary);
										}
										System.out.println("是否修改要求？如是请输入0 如否请输入1");
										if(scan.nextInt()==0){
											System.out.println("请输入修改后的要求：");
											scan.nextLine();
											String request=scan.nextLine();
											reilist.set(2,"要求："+request);
											reclist.set(i+3,request);
										}
										BufferedWriter reibw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reif),"GBK"));
										for(int j=0;j<reilist.size();j++){
											reibw.write(reilist.get(j));
											reibw.newLine();
										}
										reibw.close();
									}catch(Exception e){
									}
									System.out.println("修改成功！");
								}//修改招聘信息
								try{
									Thread.sleep(1000);
									Operator.clean();
								}catch(Exception E){
								}
							}//尚未有人应聘
							else{
								int count=0;
								for(int j=5;j<cirlist.size();j+=5){
									if((cirlist.get(j)).equals("通过")){
										count++;
									}
								}
								System.out.println("已招到 "+count+" 人，是否删除招聘？ 若是请输入0 若否请输入1");
								if(scan.nextInt()==0){
									
									for(int j=4;j<cirlist.size();j+=5){
										if((cirlist.get(j+1)).equals("审核中")){
											try{
												File bgf=new File(cirlist.get(j));
												BufferedReader bgbr=new BufferedReader(new InputStreamReader(new FileInputStream(bgf),"GBK"));
												ArrayList<String> bglist=new ArrayList<String>();
												while(bgbr.ready()){
													bglist.add(bgbr.readLine());
												}
												bglist.set(0,"拒绝");
												BufferedWriter bgbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bgf),"GBK"));
												for(int k=0;k<bglist.size();k++){
													bgbw.write(bglist.get(k));
													bgbw.newLine();
												}
												bgbw.close();
											}catch(Exception E){
											}
										}//更改审核中状态为拒绝
									}
									cirf.delete();
									(new File(reclist.get(i+4)+"\\"+recinfo+".txt")).delete();
									(new File(reclist.get(i+4))).delete();
									for(int j=i+4;j>=i;j--){
									reclist.remove(j);
									}
									System.out.println("删除成功！");
								}//删除招聘
								try{
									Thread.sleep(1000);
									Operator.clean();
								}catch(Exception E){
								}
							}//手动删除已满招聘信息
						}catch(Exception E){
						}
					}
				}
				try{
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
					for(int i=0;i<reclist.size();i++){
						bw.write(reclist.get(i));
						bw.newLine();
					}//填入修改后的招聘大屏信息
					bw.close();
				}catch(Exception E){
				}
			}catch(Exception E){
			}
		}//管理招聘信息
		else if(first==3){
			try{
				Operator.clean();
				File file=new File(Directory+"\\recruitment_list.txt");
				BufferedReader recbr=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				ArrayList<String> reclist=new ArrayList<String>();
				while(recbr.ready()){
					reclist.add(recbr.readLine());
				}
				recbr.close();
				for(int i=0;i<reclist.size();i+=5){
					if((reclist.get(i)).equals(elname)){
						try{
							File cirf=new File(reclist.get(i+4)+"\\circumstance.txt");
							BufferedReader cirbr=new BufferedReader(new InputStreamReader(new FileInputStream(cirf),"GBK"));
							ArrayList<String> cirlist=new ArrayList<String>();
							while(cirbr.ready()){
								cirlist.add(cirbr.readLine());
							}
							cirbr.close();
							for(int j=1;j<cirlist.size();j+=5){
								if((cirlist.get(j+4)).equals("审核中")){
									System.out.println("岗位："+reclist.get(i+1));
									System.out.println("月薪："+reclist.get(i+2));
									System.out.println("要求："+reclist.get(i+3));
									System.out.println();
									System.out.println("以下为该岗位的应聘者：");
									System.out.println("应聘者："+cirlist.get(j+1));
									System.out.println();
									System.out.println("以下为该应聘者个人信息：");
									try{
										File indf=new File(cirlist.get(j+2));
										BufferedReader indbr=new BufferedReader(new InputStreamReader(new FileInputStream(indf),"GBK"));
										ArrayList<String> indlist=new ArrayList<String>();
										while(indbr.ready()){
											indlist.add(indbr.readLine());
										}
										indbr.close();
										for(int k=0;k<indlist.size();k++){
											System.out.println(indlist.get(k));
										}
									}catch(Exception E){
									}//显示应聘者个人信息
									System.out.println();
									System.out.println("以下为该应聘者简历信息：");
									ArrayList<String> bglist=new ArrayList<String>();
									try{
										File bgf=new File(cirlist.get(j+3));
										BufferedReader bgbr=new BufferedReader(new InputStreamReader(new FileInputStream(bgf),"GBK"));
										
										while(bgbr.ready()){
											bglist.add(bgbr.readLine());
										}
										bgbr.close();
										for(int k=1;k<bglist.size();k++){
											System.out.println(bglist.get(k));
										}
									}catch(Exception E){
									}//显示应聘者简历信息
									System.out.println();
									System.out.println("请输入通过或拒绝");
									String state=scan.nextLine();
									cirlist.set(j+4,state);
									bglist.set(0,state);
									try{File bgf=new File(cirlist.get(j+3));
										BufferedWriter bgbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bgf),"GBK"));
										for(int k=0;k<bglist.size();k++){
											bgbw.write(bglist.get(k));
											bgbw.newLine();
										}
										bgbw.close();
										System.out.println("已"+state+"！");
										Thread.sleep(1000);
										Operator.clean();
									}catch(Exception E){
									}//更改应聘者简历上的审核状态

								}
							}
							try{
								BufferedWriter cirbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cirf),"GBK"));
								for(int k=0;k<cirlist.size();k++){
									cirbw.write(cirlist.get(k));
									cirbw.newLine();
								}
								cirbw.close();
							}catch(Exception E){
							}//更改岗位文件上应聘者的审核状态
						}catch(Exception E){
						}
					}
				}
			}catch(Exception E){
			}
		}//审核招聘信息
	}
}//用人单位类



public class Campus_Recruitment_System//校园招聘系统
{
	public static void main(String[] args){
		String directory="D:\\DataStructure_project";//文件目录
		(new File(directory)).mkdirs();
		try{
			File file=new File(directory+"\\recruitment_list.txt");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch(Exception E){
		}//招聘大屏文件
		System.out.println("学生请输入0 用人单位请输入1");
		
		Scanner scan=new Scanner(System.in);
		int first=scan.nextInt();
		if(first==0){
			(new File(directory+"\\students")).mkdirs();
			Student s=new Student(directory);
			System.out.println("如已有账户 请输入0");
			System.out.println("如需注册 请输入1");
			int second=scan.nextInt();
			try{
				if(second==1){
					s.register("students");
				}
				String stname=s.login("students");
				while(true){
					s.operate(stname);
					System.out.println("如需继续操作请输入0 退出请输入1");
					if(scan.nextInt()==1)
						break;
					System.out.flush();
					Operator.clean();
				}
			}catch(Exception E){
			}
		}//学生
		else if(first==1){
			(new File(directory+"\\employers")).mkdirs();
			Employer e=new Employer(directory);
			System.out.println("如已有账户 请输入0");
			System.out.println("如需注册   请输入1");
			int second=scan.nextInt();
			try{
				if(second==1){
					e.register("employers");
				}
				String elname=e.login("employers");
				while(true){
					e.operate(elname);
					System.out.println("如需继续操作请输入0 退出请输入1");
					if(scan.nextInt()==1)
						break;
					System.out.flush();
					Operator.clean();
				}
			}catch(Exception E){
			}
		}//用人单位

	}
}



//try{//test
//	System.out.println("调试");
//	Thread.sleep(15000);
//}catch(Exception e){
//}//test