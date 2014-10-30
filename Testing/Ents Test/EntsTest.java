
public class EntsTest{
	public static void main(String[] args){
		EntsTestEntity testent=new EntsTestEntity();
		Ents.addEnt(testent);
		
		boolean testpassed=false;
		
		Entity[] getalltest=Ents.getAll();
		testpassed=getalltest[0]==testent && getalltest.length==1;
		
		System.out.println("Ents.getAll() test passed: "+testpassed);
		
		
		testpassed=Ents.countByClass("base_entity")==1;
		System.out.println("Ents.countByClass() test passed: "+testpassed);
		
		
		testent.setPos(0,0);
		testpassed=Ents.isEntInPos(0,0);
		System.out.println("Ents.isEntInPos() test passed: "+testpassed);
		
		Entity testentinpos=Ents.getEntInPos(0,0);
		testpassed=testentinpos==testent;
		System.out.println("Ents.getEntInPos() test passed: "+testpassed);
		
		Ents.allThink();
	}
};