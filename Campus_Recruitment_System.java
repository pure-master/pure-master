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
    }//����
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
	}//����
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
	}//�鿴
	public void modify(int a,ArrayList<String> infolist){
		try{
			Operator.clean();
			System.out.println(filename);
			System.out.println("��������Ϣ��");
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
	}//�޸�
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
	}//ɾ��

}//������


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
		System.out.println("�����������û�����");
		String name=sbr.readLine();

		String secretkey=null;
		do{
			System.out.println("�������������룺");
			 secretkey=sbr.readLine();
			System.out.println("���ٴ������������룺");
			if(secretkey.equals(sbr.readLine())){
				System.out.println("ע��ɹ���");
				Thread.sleep(500);
				Operator.clean();
				break;
			}
			else{
				System.out.println("���벻һ�£����������룡");
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
	}//ע��
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
			System.out.println("�����������û�����");
			name=sbr.readLine();
			System.out.println("�������������룺");
			String secretkey=sbr.readLine();
			for(int i=0;i<num;i++){
				if(list.get(i*4+1).equals(name)&&list.get(i*4+2).equals(secretkey)){
					match=true;
					break;
				}
			}
			if(match){
				System.out.println("��¼�ɹ���");
				Thread.sleep(500);
				Operator.clean();
				break;
			}
			else{
				System.out.println("���������Ϣ�������������룡");
				Thread.sleep(500);
				Operator.clean();
			}
		}
		}catch(Exception e){
		}
		return name;
	}//��¼
}//ע���¼��




class Student extends Door{
	public Student(String Dir){
		super(Dir);
	}
	public void submit(String directory,String postdir,String stname){
		String indinfo="individual_information";
		Scanner scan=new Scanner(System.in);
		  System.out.println("��������ҪͶ�ݵļ�����ţ�");
		  String bg="Biography"+scan.nextInt();
		  try{
			  File f=new File(directory+"\\"+bg+".txt");
			  ArrayList<String> bglist=new ArrayList<String>();
			  BufferedReader bgbr=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
			  while(bgbr.ready()){
				  bglist.add(bgbr.readLine());
			  }
			  bgbr.close();
			  bglist.set(0,"�����");
			  BufferedWriter bgbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  for(int i=0;i<bglist.size();i++){
				  bgbw.write(bglist.get(i));
				  bgbw.newLine();
			  }
			  bgbw.close();
		  }catch(Exception E){
		  }//���ļ�����ӦƸ״̬
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
		  }//��¼Ͷ����Ϣ
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
			  cirlist.add("�����");
			  BufferedWriter cirbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"GBK"));
			  for(int i=0;i<cirlist.size();i++){
				  cirbw.write(cirlist.get(i));
				  cirbw.newLine();
			  }
			  cirbw.close();
		  }catch(Exception E){
		  }//�����˵�λ��¼ӦƸ��Ϣ
		  System.out.println("��Ͷ��");
		  try{
			  Thread.sleep(1000);
			  Operator.clean();
		  }catch(Exception E){
		  }
	}//Ͷ�ݼ���
	public void operate(String stname){
		String directory=Directory+"\\students\\"+stname;
		String indinfo="individual_information";
      System.out.println("����ʹ�����¹��ܣ�");
	  System.out.println("��Ҫ���и�����Ϣ���������鿴���޸ġ�ɾ���� ������0");
	  System.out.println("��Ҫ���и��˼������������鿴���޸ġ�ɾ���� ������1");
	  System.out.println("��Ҫ���в鿴���˵�λ��������Ƹ��Ϣ         ������2");
	  System.out.println("��Ҫ���в鿴��Ͷ�ݵ�ӦƸ��Ϣ�����״̬     ������3");
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
		}//��������Ϣ��������
          System.out.println("����������0");
          System.out.println("�鿴������1");
          System.out.println("�޸�������2");
          System.out.println("ɾ��������3");
		  int second=scan.nextInt();
		  Operator o=new Operator(indinfo,directory+"\\"+indinfo+".txt");
		  if(second==0){
			  while(true){
				System.out.println("��������Ҫ��������Ϣ��");
				o.add(infolist);
				System.out.println("�������������Ϣ������0 �˳�������1");
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
					System.out.println("������Ҫ�޸ĵڼ�����Ϣ��");
					o.modify(scan.nextInt()-1,infolist);
					System.out.println("��������޸�������0 �˳�������1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
	      }
	     else if(second==3){
			 System.out.println("������Ҫɾ���ڼ�����Ϣ��");
			 o.delete(scan.nextInt()-1,infolist);
	      }
	  }//������Ϣ
	  else if(first==1){
		  Operator.clean();
          System.out.println("������Ҫ�����ڼ��ݼ�����");
			String bg="Biography"+scan.nextInt();
		  ArrayList<String> infolist=new ArrayList<String>();
		try{
			File file = new File(directory+"\\"+bg+".txt");
			if (!file.exists()) {
				try {
						file.createNewFile();
						BufferedWriter bW=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
						bW.write("δͶ��");
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
		}//��������������
		  Operator.clean();
          System.out.println("����������0");
          System.out.println("�鿴������1");
          System.out.println("�޸�������2");
          System.out.println("ɾ��������3");
		  int second=scan.nextInt();
		  Operator o=new Operator(bg,directory+"\\"+bg+".txt");
		  if(second==0){
				while(true){
					System.out.println("��������Ҫ��������Ϣ��");
					o.add(infolist);
					System.out.println("�������������Ϣ������0 �˳�������1");
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
					System.out.println("������Ҫ�޸ĵڼ�����Ϣ��");
					o.modify(scan.nextInt(),infolist);
					System.out.println("��������޸�������0 �˳�������1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
	      }
	     else if(second==3){
			 System.out.println("������Ҫɾ���ڼ�����Ϣ��");
			 o.delete(scan.nextInt(),infolist);
	      }
	  }//���˼���
	  else if(first==2){
		  System.out.println("��Ҫ�����˵�λ�鿴 ������0");
		  System.out.println("��Ҫ����λ�鿴     ������1");
		  System.out.println("��Ҫ��н��鿴     ������2");
		  int second=scan.nextInt();
		  if(second==0){
			  System.out.println("����������ҪӦƸ�����˵�λ���ƣ�");
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
						  System.out.println("���˵�λ���ƣ�"+reclist.get(i));
						  System.out.println("��λ��"+reclist.get(i+1));
						  System.out.println("��н��"+reclist.get(i+2));
						  System.out.println("Ҫ��"+reclist.get(i+3));
						  System.out.println("�Ƿ�Ͷ�ݣ� ����������0 ����������1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+4),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("�����˵�λ��δ������Ƹ��Ϣ");
				  br.close();
			  }catch(Exception E){
			  }
		  }//�����˵�λ�鿴
		  else if(second==1){
			  System.out.println("����������ҪӦƸ�ĸ�λ���ƣ�");
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
						  System.out.println("���˵�λ���ƣ�"+reclist.get(i-1));
						  System.out.println("��λ��"+reclist.get(i));
						  System.out.println("��н��"+reclist.get(i+1));
						  System.out.println("Ҫ��"+reclist.get(i+2));
						  System.out.println("�Ƿ�Ͷ�ݣ� ����������0 ����������1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+3),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("��δ�����˵�λ�����ø�λ����Ƹ��Ϣ");
				  br.close();
			  }catch(Exception E){
			  }
		  }//����λ�鿴
		  else if(second==2){
			  System.out.println("����������Ҫ����н���ޣ��������֣���");
			  int minsalary=scan.nextInt();
			  System.out.println("����������Ҫ����н���ޣ��������֣���");
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
						  System.out.println("���˵�λ���ƣ�"+reclist.get(i-2));
						  System.out.println("��λ��"+reclist.get(i-1));
						  System.out.println("��н��"+reclist.get(i));
						  System.out.println("Ҫ��"+reclist.get(i+1));
						  System.out.println("�Ƿ�Ͷ�ݣ� ����������0 ����������1");
						  if(scan.nextInt()==0){
							  submit(directory,reclist.get(i+2),stname);
						  }
					  }
				  }
				  if(!found)
					  System.out.println("��н�ڸ÷�Χ�ڵ���Ƹ��Ϣ��δ����");
				  br.close();
			  }catch(Exception E){
			  }
		  }//��н��鿴
	  }//�鿴��Ƹ��Ϣ��Ͷ�ݼ���
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
					  System.out.println("����ƸĿǰ�����״̬Ϊ��"+cirbr.readLine());
					  cirbr.close();
				  }catch(Exception E){
				  }
				  try{
					  File file=new File(recdir);
					  ArrayList<String> reclist=new ArrayList<String>();
					  BufferedReader recbr=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
					  System.out.println("����Ƹ��Ϣ���£�");
					  while(recbr.ready()){
						  reclist.add(recbr.readLine());
					  }
					  recbr.close();
					  for(int j=0;j<reclist.size();j++){
						  System.out.println(reclist.get(j));
					  }
				  }catch(Exception E){
					  System.out.println("����Ƹ������");
				  }
			  }
		  }catch(Exception E){
		  }
	  }//�鿴��Ͷ�ݵ�ӦƸ��Ϣ�����״̬
	}
}//ѧ����

class Employer extends Door{
	public Employer(String Dir){
		super(Dir);
	}
	public void operate(String elname){
		String directory=Directory+"\\employers\\"+elname;
		String elinfo="employer_information";//���˵�λ��Ϣ
		String recinfo="recruitment_information";//��Ƹ��Ϣ
		Scanner scan=new Scanner(System.in);
		System.out.println();
		System.out.println("���ɽ������²�����");
		System.out.println("��Ҫ�������˵�λ��Ϣ���������鿴���޸ġ�ɾ���� ������0");
		System.out.println("��Ҫ������Ƹ��Ϣ                               ������1");
		System.out.println("��Ҫ������Ƹ��Ϣ                               ������2");
		System.out.println("��Ҫ�����Ƹ��Ϣ                               ������3");
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
			}//��������Ϣ��������
			  System.out.println();
			 System.out.println("����������0");
			 System.out.println("�鿴������1");
			 System.out.println("�޸�������2");
			System.out.println("ɾ��������3");
			int second=scan.nextInt();
			 Operator o=new Operator(elinfo,directory+"\\"+elinfo+".txt");
			 if(second==0){
			  while(true){
				System.out.println("��������Ҫ��������Ϣ��");
				o.add(infolist);
				System.out.println("�������������Ϣ������0 �˳�������1");
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
					System.out.println("������Ҫ�޸ĵڼ�����Ϣ��");
					o.modify(scan.nextInt()-1,infolist);
					System.out.println("��������޸�������0 �˳�������1");
					if(scan.nextInt()==1)
					break;
					System.out.flush();
					Operator.clean();
			  }
			 }
			else if(second==3){
				 System.out.println("������Ҫɾ���ڼ�����Ϣ��");
				 o.delete(scan.nextInt()-1,infolist);
			 }
		}//���˵�λ��Ϣ
		else if(first==1){
			Operator.clean();
		  System.out.println("��������Ҫ��������Ƹ��λ����");
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
			  bw.write("��λ��"+post);
			  bw.newLine();
			  System.out.println("��������н���������֣���");
			  String wage=scan.nextLine();
			  bw.write("��н��"+wage);
			  bw.newLine();
			  System.out.println("������ӦƸҪ��");
			  String request=scan.nextLine();
			  bw.write("Ҫ��"+request);
			  bw.newLine();
			  bw.close();
			  //��Ƹ��Ϣ
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
				  reclist.add(directory+"\\post_"+post);//��λ�������˵�λ��Ϣ�ļ�
				  for(int i=0;i<reclist.size();i++){
					  bw1.write(reclist.get(i));
					  bw1.newLine();
				  }
				  bw1.close();
			  }catch(Exception e){
			  }//��Ƹ��Ϣ����
		  }catch(Exception e){
		  }
		  System.out.println("�ѷ�����");
		}//������Ƹ��Ϣ
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
							System.out.println("����Ƹ��Ϣ�������£�");
							System.out.println("���˵�λ����"+reclist.get(i));
							System.out.println("��λ����"+reclist.get(i+1));
							System.out.println("��н��"+reclist.get(i+2));
							System.out.println("Ҫ��"+reclist.get(i+3));
							System.out.println();
							if((cirlist.get(0)).equals("0")){
								System.out.println("����Ƹ��δ�յ�Ͷ�ݣ��Ƿ��޸���Ϣ������������0 ���������1");
								if(scan.nextInt()==0){
									try{
										File reif=new File(reclist.get(i+4)+"\\"+recinfo+".txt");
										BufferedReader reibr=new BufferedReader(new InputStreamReader(new FileInputStream(reif),"GBK"));
										ArrayList<String> reilist=new ArrayList<String>();
										while(reibr.ready()){
											reilist.add(reibr.readLine());
										}
										reibr.close();
										System.out.println("�Ƿ��޸ĸ�λ������������0 ���������1");
										if(scan.nextInt()==0){
											System.out.println("�������޸ĺ�ĸ�λ��");
											scan.nextLine();
											String postname=scan.nextLine();
											reilist.set(0,"��λ��"+postname);
											reclist.set(i+1,postname);
										}
										System.out.println("�Ƿ��޸���н������������0 ���������1");
										if(scan.nextInt()==0){
											System.out.println("�������޸ĺ����н��");
											scan.nextLine();
											String salary=scan.nextLine();
											reilist.set(1,"��н��"+salary);
											reclist.set(i+2,salary);
										}
										System.out.println("�Ƿ��޸�Ҫ������������0 ���������1");
										if(scan.nextInt()==0){
											System.out.println("�������޸ĺ��Ҫ��");
											scan.nextLine();
											String request=scan.nextLine();
											reilist.set(2,"Ҫ��"+request);
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
									System.out.println("�޸ĳɹ���");
								}//�޸���Ƹ��Ϣ
								try{
									Thread.sleep(1000);
									Operator.clean();
								}catch(Exception E){
								}
							}//��δ����ӦƸ
							else{
								int count=0;
								for(int j=5;j<cirlist.size();j+=5){
									if((cirlist.get(j)).equals("ͨ��")){
										count++;
									}
								}
								System.out.println("���е� "+count+" �ˣ��Ƿ�ɾ����Ƹ�� ����������0 ����������1");
								if(scan.nextInt()==0){
									
									for(int j=4;j<cirlist.size();j+=5){
										if((cirlist.get(j+1)).equals("�����")){
											try{
												File bgf=new File(cirlist.get(j));
												BufferedReader bgbr=new BufferedReader(new InputStreamReader(new FileInputStream(bgf),"GBK"));
												ArrayList<String> bglist=new ArrayList<String>();
												while(bgbr.ready()){
													bglist.add(bgbr.readLine());
												}
												bglist.set(0,"�ܾ�");
												BufferedWriter bgbw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bgf),"GBK"));
												for(int k=0;k<bglist.size();k++){
													bgbw.write(bglist.get(k));
													bgbw.newLine();
												}
												bgbw.close();
											}catch(Exception E){
											}
										}//���������״̬Ϊ�ܾ�
									}
									cirf.delete();
									(new File(reclist.get(i+4)+"\\"+recinfo+".txt")).delete();
									(new File(reclist.get(i+4))).delete();
									for(int j=i+4;j>=i;j--){
									reclist.remove(j);
									}
									System.out.println("ɾ���ɹ���");
								}//ɾ����Ƹ
								try{
									Thread.sleep(1000);
									Operator.clean();
								}catch(Exception E){
								}
							}//�ֶ�ɾ��������Ƹ��Ϣ
						}catch(Exception E){
						}
					}
				}
				try{
					BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
					for(int i=0;i<reclist.size();i++){
						bw.write(reclist.get(i));
						bw.newLine();
					}//�����޸ĺ����Ƹ������Ϣ
					bw.close();
				}catch(Exception E){
				}
			}catch(Exception E){
			}
		}//������Ƹ��Ϣ
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
								if((cirlist.get(j+4)).equals("�����")){
									System.out.println("��λ��"+reclist.get(i+1));
									System.out.println("��н��"+reclist.get(i+2));
									System.out.println("Ҫ��"+reclist.get(i+3));
									System.out.println();
									System.out.println("����Ϊ�ø�λ��ӦƸ�ߣ�");
									System.out.println("ӦƸ�ߣ�"+cirlist.get(j+1));
									System.out.println();
									System.out.println("����Ϊ��ӦƸ�߸�����Ϣ��");
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
									}//��ʾӦƸ�߸�����Ϣ
									System.out.println();
									System.out.println("����Ϊ��ӦƸ�߼�����Ϣ��");
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
									}//��ʾӦƸ�߼�����Ϣ
									System.out.println();
									System.out.println("������ͨ����ܾ�");
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
										System.out.println("��"+state+"��");
										Thread.sleep(1000);
										Operator.clean();
									}catch(Exception E){
									}//����ӦƸ�߼����ϵ����״̬

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
							}//���ĸ�λ�ļ���ӦƸ�ߵ����״̬
						}catch(Exception E){
						}
					}
				}
			}catch(Exception E){
			}
		}//�����Ƹ��Ϣ
	}
}//���˵�λ��



public class Campus_Recruitment_System//У԰��Ƹϵͳ
{
	public static void main(String[] args){
		String directory="D:\\DataStructure_project";//�ļ�Ŀ¼
		(new File(directory)).mkdirs();
		try{
			File file=new File(directory+"\\recruitment_list.txt");
			if(!file.exists()){
				file.createNewFile();
			}
		}catch(Exception E){
		}//��Ƹ�����ļ�
		System.out.println("ѧ��������0 ���˵�λ������1");
		
		Scanner scan=new Scanner(System.in);
		int first=scan.nextInt();
		if(first==0){
			(new File(directory+"\\students")).mkdirs();
			Student s=new Student(directory);
			System.out.println("�������˻� ������0");
			System.out.println("����ע�� ������1");
			int second=scan.nextInt();
			try{
				if(second==1){
					s.register("students");
				}
				String stname=s.login("students");
				while(true){
					s.operate(stname);
					System.out.println("�����������������0 �˳�������1");
					if(scan.nextInt()==1)
						break;
					System.out.flush();
					Operator.clean();
				}
			}catch(Exception E){
			}
		}//ѧ��
		else if(first==1){
			(new File(directory+"\\employers")).mkdirs();
			Employer e=new Employer(directory);
			System.out.println("�������˻� ������0");
			System.out.println("����ע��   ������1");
			int second=scan.nextInt();
			try{
				if(second==1){
					e.register("employers");
				}
				String elname=e.login("employers");
				while(true){
					e.operate(elname);
					System.out.println("�����������������0 �˳�������1");
					if(scan.nextInt()==1)
						break;
					System.out.flush();
					Operator.clean();
				}
			}catch(Exception E){
			}
		}//���˵�λ

	}
}



//try{//test
//	System.out.println("����");
//	Thread.sleep(15000);
//}catch(Exception e){
//}//test