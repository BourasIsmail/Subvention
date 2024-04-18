package ma.entraide.subvention;

import jakarta.annotation.PostConstruct;
import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.entity.Deleguation;
import ma.entraide.subvention.entity.UserInfo;
import ma.entraide.subvention.service.CoordinationService;
import ma.entraide.subvention.service.DeleguationService;
import ma.entraide.subvention.service.DemandeService;
import ma.entraide.subvention.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

	@Autowired
	public CoordinationService coordinationService;
	@Autowired
	public DeleguationService delegationService;
	@Autowired
	public UserInfoService userInfoService;

	public static Logger logger = LoggerFactory.getLogger(SpringSecurityApplication.class);

	@PostConstruct
	public void init() {
		logger.info("Appilcation started ...");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(DeleguationService deleguationService) {
		return args ->{
			logger.info("Running Spring Security Application ...");
			//Admin Account

			UserInfo admin = new UserInfo("test","admin@gmailcom","USER_ROLES","123456");
			userInfoService.addUser(admin);
			//Coordinations
			/*
			Coordination coordination1 = new Coordination("طنجة-تطوان-الحسيمة");
			Coordination coordination2 = new Coordination("الشرق");
			Coordination coordination3 = new Coordination("فاس-مكناس");
			Coordination coordination4 = new Coordination("الرباط-سلا-القنيطرة");
			Coordination coordination5 = new Coordination("بني ملال-خنيفرة");
			Coordination coordination6 = new Coordination("الدارالبيضاء-سطات");
			Coordination coordination7 = new Coordination("مراكش-اسفي");
			Coordination coordination8 = new Coordination("درعة-تافيلالت");
			Coordination coordination9 = new Coordination("سوس-ماسة");
			Coordination coordination10 = new Coordination("كلميم-واد نون");
			Coordination coordination11 = new Coordination("العيون-الساقية الحمراء");
			Coordination coordination12 = new Coordination("الداخلة-وادي الذهب");
			*/


			//Delegation
			/*
			Deleguation deleguation1 = new Deleguation("طنجة اصيلة",coordination1);
			Deleguation deleguation2 = new Deleguation("المضيق الفنيدق",coordination1);
			Deleguation deleguation3 = new Deleguation("تطوان",coordination1);
			Deleguation deleguation4 = new Deleguation("الفحص انجرة",coordination1);
			Deleguation deleguation5 = new Deleguation("العرائش",coordination1);
			Deleguation deleguation6 = new Deleguation("الحسيمة",coordination1);
			Deleguation deleguation7 = new Deleguation("شفشاون",coordination1);
			Deleguation deleguation8 = new Deleguation("وزان",coordination1);
			Deleguation deleguation9 = new Deleguation("وجدة-انجاد",coordination2);
			Deleguation deleguation10 = new Deleguation("الناظور",coordination2);
			Deleguation deleguation11 = new Deleguation("الدريوش",coordination2);
			Deleguation deleguation12 = new Deleguation("جرادة",coordination2);
			Deleguation deleguation13 = new Deleguation("بركان",coordination2);
			Deleguation deleguation14 = new Deleguation("تاوريرت",coordination2);
			Deleguation deleguation15 = new Deleguation("كرسيف",coordination2);
			Deleguation deleguation16 = new Deleguation("فكيك",coordination2);
			Deleguation deleguation17 = new Deleguation("فاس",coordination3);
			Deleguation deleguation18 = new Deleguation("مكناس",coordination3);
			Deleguation deleguation19 = new Deleguation("تازة",coordination3);
			Deleguation deleguation20 = new Deleguation("الحاجب",coordination3);
			Deleguation deleguation21 = new Deleguation("افران",coordination3);
			Deleguation deleguation22 = new Deleguation("مولاي يعقوب",coordination3);
			Deleguation deleguation23 = new Deleguation("صفرو",coordination3);
			Deleguation deleguation24 = new Deleguation("بولمان",coordination3);
			Deleguation deleguation25 = new Deleguation("تاونات",coordination3);
			Deleguation deleguation26 = new Deleguation("الرباط",coordination4);
			Deleguation deleguation27 = new Deleguation("سلا",coordination4);
			Deleguation deleguation28 = new Deleguation("الصخيرات تمارة",coordination4);
			Deleguation deleguation29 = new Deleguation("القنيطرة",coordination4);
			Deleguation deleguation30 = new Deleguation("الخميسات",coordination4);
			Deleguation deleguation31 = new Deleguation("سيدي قاسم",coordination4);
			Deleguation deleguation32 = new Deleguation("سيدي سليمان",coordination4);
			Deleguation deleguation33 = new Deleguation("بني ملال",coordination5);
			Deleguation deleguation34 = new Deleguation("ازيلال",coordination5);
			Deleguation deleguation35 = new Deleguation("الفقيه بن صالح",coordination5);
			Deleguation deleguation36 = new Deleguation("خنيفرة",coordination5);
			Deleguation deleguation37 = new Deleguation("خريبكة",coordination5);
			Deleguation deleguation38 = new Deleguation("الدار البيضاء انفا",coordination6);
			Deleguation deleguation39 = new Deleguation("المحمدية",coordination6);
			Deleguation deleguation40 = new Deleguation("الجديدة",coordination6);
			Deleguation deleguation41 = new Deleguation("النواصر",coordination6);
			Deleguation deleguation42 = new Deleguation("مديونة",coordination6);
			Deleguation deleguation43 = new Deleguation("بنسليمان",coordination6);
			Deleguation deleguation44 = new Deleguation("برشيد",coordination6);
			Deleguation deleguation45 = new Deleguation("سطات",coordination6);
			Deleguation deleguation46 = new Deleguation("الفداء مرالسلطان",coordination6);
			Deleguation deleguation47 = new Deleguation("عين السبع-الحي المحمدي",coordination6);
			Deleguation deleguation48 = new Deleguation("سيدي البرنوصي",coordination6);
			Deleguation deleguation49 = new Deleguation("الحي الحسني",coordination6);
			Deleguation deleguation50 = new Deleguation("عين الشق",coordination6);
			Deleguation deleguation51 = new Deleguation("ابن مسيك سيدي عثمان",coordination6);
			Deleguation deleguation52 = new Deleguation("مولاي رشيد",coordination6);
			Deleguation deleguation53 = new Deleguation("الرحامنة",coordination7);
			Deleguation deleguation54 = new Deleguation("اسفي",coordination7);
			Deleguation deleguation55 = new Deleguation("اليوسفية",coordination7);
			Deleguation deleguation56 = new Deleguation("الراشيدية",coordination8);
			Deleguation deleguation57 = new Deleguation("ورزازات",coordination8);
			Deleguation deleguation58 = new Deleguation("ميدلت",coordination8);
			Deleguation deleguation59 = new Deleguation("تنغير",coordination8);
			Deleguation deleguation60 = new Deleguation("زاكورة",coordination8);
			Deleguation deleguation61 = new Deleguation("اكادير ادا اوتانان",coordination9);
			Deleguation deleguation62 = new Deleguation("انزكان ايت ملول",coordination9);
			Deleguation deleguation63 = new Deleguation("اشتوكة ايت باها",coordination9);
			Deleguation deleguation64 = new Deleguation("تارودانت",coordination9);
			Deleguation deleguation65 = new Deleguation("تزنيت",coordination9);
			Deleguation deleguation66 = new Deleguation("طاطا",coordination9);
			Deleguation deleguation67 = new Deleguation("كلميم",coordination10);
			Deleguation deleguation68 = new Deleguation("اسا زاك",coordination10);
			Deleguation deleguation69 = new Deleguation("طانطان",coordination10);
			Deleguation deleguation70 = new Deleguation("سيدي افني",coordination10);
			Deleguation deleguation71 = new Deleguation("العيون",coordination11);
			Deleguation deleguation72 = new Deleguation("بوجدور",coordination11);
			Deleguation deleguation73 = new Deleguation("طرفاية",coordination11);
			Deleguation deleguation74 = new Deleguation("السمارة",coordination11);

			Deleguation deleguation75 = new Deleguation("وادي الذهب",coordination12);
			Deleguation deleguation76 = new Deleguation("اوسرد",coordination12);

			Deleguation deleguation77 = new Deleguation("سيدي بنور",coordination6);
			Deleguation deleguation78 = new Deleguation("مراكش",coordination7);
			Deleguation deleguation79 = new Deleguation("الصويرة",coordination7);
			Deleguation deleguation80 = new Deleguation("الحوز",coordination7);
			Deleguation deleguation81 = new Deleguation("قلعة السراغنة",coordination7);
			Deleguation deleguation82 = new Deleguation("شيشاوة",coordination7);

			coordinationService.addCoordination(coordination1);
			coordinationService.addCoordination(coordination2);
			coordinationService.addCoordination(coordination3);
			coordinationService.addCoordination(coordination4);
			coordinationService.addCoordination(coordination5);
			coordinationService.addCoordination(coordination6);
			coordinationService.addCoordination(coordination7);
			coordinationService.addCoordination(coordination8);
			coordinationService.addCoordination(coordination9);
			coordinationService.addCoordination(coordination10);
			coordinationService.addCoordination(coordination11);
coordinationService.addCoordination(coordination12);
*/

			 /*
			deleguationService.addDeleguation(deleguation1);
			deleguationService.addDeleguation(deleguation2);
			deleguationService.addDeleguation(deleguation3);
			deleguationService.addDeleguation(deleguation4);
			deleguationService.addDeleguation(deleguation5);
			deleguationService.addDeleguation(deleguation6);
			deleguationService.addDeleguation(deleguation7);
			deleguationService.addDeleguation(deleguation8);
			deleguationService.addDeleguation(deleguation9);
			deleguationService.addDeleguation(deleguation10);
			deleguationService.addDeleguation(deleguation11);
			deleguationService.addDeleguation(deleguation12);
			deleguationService.addDeleguation(deleguation13);
			deleguationService.addDeleguation(deleguation14);
			deleguationService.addDeleguation(deleguation15);
			deleguationService.addDeleguation(deleguation16);
			deleguationService.addDeleguation(deleguation17);
			deleguationService.addDeleguation(deleguation18);
			deleguationService.addDeleguation(deleguation19);
			deleguationService.addDeleguation(deleguation20);
			deleguationService.addDeleguation(deleguation21);
			deleguationService.addDeleguation(deleguation22);
			deleguationService.addDeleguation(deleguation23);
			deleguationService.addDeleguation(deleguation24);
			deleguationService.addDeleguation(deleguation25);
			deleguationService.addDeleguation(deleguation26);
			deleguationService.addDeleguation(deleguation27);
			deleguationService.addDeleguation(deleguation28);
			deleguationService.addDeleguation(deleguation29);
			deleguationService.addDeleguation(deleguation30);
			deleguationService.addDeleguation(deleguation31);
			deleguationService.addDeleguation(deleguation32);
			deleguationService.addDeleguation(deleguation33);
			deleguationService.addDeleguation(deleguation34);
			deleguationService.addDeleguation(deleguation35);
			deleguationService.addDeleguation(deleguation36);
			deleguationService.addDeleguation(deleguation37);
			deleguationService.addDeleguation(deleguation38);
			deleguationService.addDeleguation(deleguation39);
			deleguationService.addDeleguation(deleguation40);
			deleguationService.addDeleguation(deleguation41);
			deleguationService.addDeleguation(deleguation42);
			deleguationService.addDeleguation(deleguation43);
			deleguationService.addDeleguation(deleguation44);
			deleguationService.addDeleguation(deleguation45);
			deleguationService.addDeleguation(deleguation46);
			deleguationService.addDeleguation(deleguation47);
			deleguationService.addDeleguation(deleguation48);
			deleguationService.addDeleguation(deleguation49);
			deleguationService.addDeleguation(deleguation50);
			deleguationService.addDeleguation(deleguation51);
			deleguationService.addDeleguation(deleguation52);
			deleguationService.addDeleguation(deleguation53);
			deleguationService.addDeleguation(deleguation54);
			deleguationService.addDeleguation(deleguation55);
			deleguationService.addDeleguation(deleguation56);
			deleguationService.addDeleguation(deleguation57);
			deleguationService.addDeleguation(deleguation58);
			deleguationService.addDeleguation(deleguation59);
			deleguationService.addDeleguation(deleguation60);
			deleguationService.addDeleguation(deleguation61);
			deleguationService.addDeleguation(deleguation62);
			deleguationService.addDeleguation(deleguation63);
			deleguationService.addDeleguation(deleguation64);
			deleguationService.addDeleguation(deleguation65);
			deleguationService.addDeleguation(deleguation66);
			deleguationService.addDeleguation(deleguation67);
			deleguationService.addDeleguation(deleguation68);
			deleguationService.addDeleguation(deleguation69);
			deleguationService.addDeleguation(deleguation70);
			deleguationService.addDeleguation(deleguation71);
			deleguationService.addDeleguation(deleguation72);
			deleguationService.addDeleguation(deleguation73);
			deleguationService.addDeleguation(deleguation74);
			deleguationService.addDeleguation(deleguation75);
			deleguationService.addDeleguation(deleguation76);
			deleguationService.addDeleguation(deleguation77);
			deleguationService.addDeleguation(deleguation78);
			deleguationService.addDeleguation(deleguation79);
			deleguationService.addDeleguation(deleguation80);
			deleguationService.addDeleguation(deleguation81);
			deleguationService.addDeleguation(deleguation82);
*/
//Users


			logger.info("end");
		};
	}

}
