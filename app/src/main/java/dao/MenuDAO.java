package dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import model.Menu;

public class MenuDAO {
    DatabaseReference mData;

    public MenuDAO() {
        mData = FirebaseDatabase.getInstance().getReference();
    }

    //put data Menu
    public void putData() {
        ArrayList<Menu> list = new ArrayList<>();

        //CH01
        list.add(new Menu("CH01_1", "Green salad", 57000, "https://images.foody.vn/res/g10/90677/s120x120/076d7ea8-cc8b-4d1d-e4cb-42a221f62267.jpg"));
        list.add(new Menu("CH01_2", "Chicken salad", 82000, "https://images.foody.vn/res/g10/90677/s120x120/3ed372ea-a9c8-4751-e490-c24df299fc0c.jpg"));
        list.add(new Menu("CH01_3", "Buzza salad", 98000, "https://images.foody.vn/res/g10/90677/s120x120/a63a8465-6f25-4496-21c1-05f27b6529dd.jpg"));
        list.add(new Menu("CH01_4", "Beef salad", 131000, "https://images.foody.vn/res/g10/90677/s120x120/54935bb2-7ee3-48d7-56d8-a666e0862a04.jpg"));
        list.add(new Menu("CH01_5", "Fried shrimp salad", 114000, "https://images.foody.vn/res/g10/90677/s120x120/b3d689f0-7f29-4e02-0293-0329a1ae4046.jpg"));

        //CH02
        list.add(new Menu("CH02_1", "Bánh bao kẹp 2 vị", 107800, "https://images.foody.vn/res/g18/179996/s120x120/2018119155046-ba%cc%81nh-bao-ke%cc%a3p.jpg"));
        list.add(new Menu("CH02_2", "Vịt quay truyền thống", 319000, "https://images.foody.vn/res/g18/179996/s120x120/2018911161456-vit-quay-truye%cc%80n-tho%cc%81ng.jpg"));
        list.add(new Menu("CH02_3", "Chân gà hấp tàu xì", 66000, "https://images.foody.vn/res/g18/179996/s120x120/foody-e17.chan%20ga%20hap%20tau%20xi-635989962378419251.jpg"));
        list.add(new Menu("CH02_4", "Há cảo tôm sò điệp tươi", 71500, "https://images.foody.vn/res/g18/179996/s120x120/2017629154617-ha-cao-so-diep-copy.jpg"));
        list.add(new Menu("CH02_5", "Há cảo tôm bó xôi", 66000, "https://images.foody.vn/res/g18/179996/s120x120/20181110162059-ha-cao-tom-bo-xoi-6pcs-va-3pcs-.jpg"));

        //CH03
        list.add(new Menu("CH03_1", "Salmon ebiko mayo roll", 82500, "https://images.foody.vn/res/g14/135147/s120x120/29c81b82-6773-4c8f-ca5f-9e2265b1c963.jpg"));
        list.add(new Menu("CH03_2", "Unagi cheese crispy roll", 165000, "https://images.foody.vn/res/g14/135147/s120x120/d4bb078b-d8f3-4f3f-3e4c-24cb7d3d9495.jpg"));
        list.add(new Menu("CH03_3", "Salmon mentaiyaki", 192500, "https://images.foody.vn/res/g14/135147/s120x120/0877552a-9ff7-4867-0cf1-9054b90d0c03.jpg"));
        list.add(new Menu("CH03_4", "Ebi mentaiyaki", 165000, "https://images.foody.vn/res/g14/135147/s120x120/3020f99d-58f6-4df7-29e7-0faa176e43cb.jpg"));
        list.add(new Menu("CH03_5", "Yakushima set", 218900, "https://images.foody.vn/res/g14/135147/s120x120/63f2f4bc-d834-4273-2959-817f174690e4.jpg"));

        //CH04
        list.add(new Menu("CH04_1", "Mì kim chi hải sản", 49000, "https://images.foody.vn/res/g22/216691/s120x120/2016922143620-mi-kim-chi-hai-san.jpg"));
        list.add(new Menu("CH04_2", "Mì TomYum bò", 49000, "https://images.foody.vn/res/g22/216691/s120x120/928588b2-2a21-4501-8937-01f084a24612.jpg"));
        list.add(new Menu("CH04_3", "Đùi gà cay", 49000, "https://images.foody.vn/res/g22/216691/s120x120/81819c1d-5949-4a62-a526-bdef099fd53a.jpg"));
        list.add(new Menu("CH04_4", "Mì xào gà", 49000, "https://images.foody.vn/res/g22/216691/s120x120/6dd4a05c-b8a7-48d3-a4c4-7e3ca2f532da.jpg"));
        list.add(new Menu("CH04_5", "Xúc xích", 15000, "https://images.foody.vn/res/g22/216691/s120x120/2e7f3c7f-bf8b-45d6-806f-42347e2a6447.jpg"));

        //CH05
        list.add(new Menu("CH05_1", "Chả cá chiên với sốt cay chua ngọt", 104500, "https://images.foody.vn/res/g9/88048/s120x120/20181211105425-tt-starter-05.jpg"));
        list.add(new Menu("CH05_2", "Cánh gà chiên với sả sợi", 126500, "https://images.foody.vn/res/g9/88048/s120x120/20181211105355-tt-starter-03.jpg"));
        list.add(new Menu("CH05_3", "Nghêu xào với rau quế và sa tế Thái", 126500, "https://images.foody.vn/res/g9/88048/s120x120/20181211105443-tt-starter-06.jpg"));
        list.add(new Menu("CH05_4", "Gà bọc lá dứa chiên", 99000, "https://images.foody.vn/res/g9/88048/s120x120/2018121110548-tt-starter-04.jpg"));
        list.add(new Menu("CH05_5", "Sò huyết sốt me chua cay", 170500, "https://images.foody.vn/res/g9/88048/s120x120/2018121110558-tt-starter-07.jpg"));


        //CH06
        list.add(new Menu("CH06_1", "Latte", 39000, "https://images.foody.vn/res/g7/67129/s120x120/2017525173134-latte.jpg"));
        list.add(new Menu("CH06_2", "Cappuccino", 39000, "https://images.foody.vn/res/g7/67129/s120x120/20181212164428-cappuchino.jpg"));
        list.add(new Menu("CH06_3", "Mocha", 46000, "https://images.foody.vn/res/g7/67129/s120x120/2017525173123-mocha.jpg"));
        list.add(new Menu("CH06_4", "Peach tea", 43000, "https://images.foody.vn/res/g7/67129/s120x120/2018822115344-peach-tea.jpg"));
        list.add(new Menu("CH06_5", "Thai milktea", 38000, "https://images.foody.vn/res/g7/67129/s120x120/201731811914-thai-milktea-33-38-.jpg"));

        //CH07
        list.add(new Menu("CH07_1", "Sữa tươi trân châu đường đen", 62000, "https://images.foody.vn/res/g71/700458/s120x120/988d741d-f41a-4e8f-9fc0-f7a28312ed67.jpg"));
        list.add(new Menu("CH07_2", "Trà chanh bí đao", 52000, "https://images.foody.vn/res/g71/700458/s120x120/94b2034f-1bd8-4933-3e4b-13bab9ac31a6.jpg"));
        list.add(new Menu("CH07_3", "Matcha Oolong đào", 65000, "https://images.foody.vn/res/g71/700458/s120x120/6a73d502-4bdf-4def-70db-4f1009a4aa07.jpg"));
        list.add(new Menu("CH07_4", "Trà sữa Thiết Quan Âm", 52000, "https://images.foody.vn/res/g71/700458/s120x120/5fd8f214-cd03-48de-22df-a07aff4b0405.jpg"));
        list.add(new Menu("CH07_5", "Lục trà nhài", 41000, "https://images.foody.vn/res/g71/700458/s120x120/b9a57527-8c65-404a-b975-c616c05ec1b1.jpg"));

        //CH08
        list.add(new Menu("CH08_1", "Salted lemon arabica", 49000, "https://images.foody.vn/res/g31/307624/s120x120/267fc4d6-120a-4364-8671-a8033d8f0e0f.jpg"));
        list.add(new Menu("CH08_2", "Arabica bạc xỉu", 45000, "https://images.foody.vn/res/g31/307624/s120x120/46755ef9-320a-4986-da88-8bdf64f820dd.jpg"));
        list.add(new Menu("CH08_3", "Lemon golden oolong", 49000, "https://images.foody.vn/res/g31/307624/s120x120/13ae9b24-2af9-4246-ad5b-75ee479f5a8a.jpg"));
        list.add(new Menu("CH08_4", "Perfect peach tea", 45000, "https://images.foody.vn/res/g31/307624/s120x120/12a46a83-6d59-4226-404b-3b2939f5777f.jpg"));
        list.add(new Menu("CH08_5", "Cherry oolong cold brew", 65000, "https://images.foody.vn/res/g31/307624/s120x120/b19cd50a-3781-4681-ae6e-03fa019bed66.jpg"));

        //CH09
        list.add(new Menu("CH09_1", "Matcha Đá Xay Phô Mai", 58000, "https://images.foody.vn/res/g19/187688/s120x120/2018621144131-matcha-da-xay-pho-mai.jpg"));
        list.add(new Menu("CH09_2", "Cacao Cốt Dừa Đá Xay Phô Mai", 45000, "https://images.foody.vn/res/g19/187688/s120x120/a9e4e0ae-3f44-4152-b173-35a0573b38cc.jpg"));
        list.add(new Menu("CH09_3", "Macchiato Coffee", 45000, "https://images.foody.vn/res/g19/187688/s120x120/81c3d0a8-bad7-4d2e-8ce8-5fbc693028ff.jpg"));
        list.add(new Menu("CH09_4", "Ice Chocolate", 58000, "https://images.foody.vn/res/g19/187688/s120x120/201862114419-ice-chocolate.jpg"));
        list.add(new Menu("CH09_5", "Americano", 35000, "https://images.foody.vn/res/g19/187688/s120x120/2018123225111-img_0307.jpg"));

        //CH10
        list.add(new Menu("CH010_1", "Café sầu riêng", 34000, "https://images.foody.vn/res/g71/702843/s120x120/8f6a78e2-00d3-4913-9b04-4479182c0c27.jpg"));
        list.add(new Menu("CH010_2", "Trà đào thảo mộc", 42000, "https://images.foody.vn/res/g71/702843/s120x120/ce039d73-f284-4fd0-c2df-b671094a1cc5.jpg"));
        list.add(new Menu("CH010_3", "Teh Tarik - trà sữa nóng", 27000, "https://images.foody.vn/res/g19/187688/s120x120/81c3d0a8-bad7-4d2e-8ce8-5fbc693028ff.jpg"));
        list.add(new Menu("CH010_4", "Mint choco", 45000, "https://images.foody.vn/res/g71/702843/s120x120/99fe5ecd-414a-4b8c-edf2-1c9934f6b845.jpg"));
        list.add(new Menu("CH010_5", "Trà sữa Ice Jelly", 45000, "https://images.foody.vn/res/g71/702843/s120x120/2d3d4f30-816c-417b-9b26-f23de1639003.jpg"));

        //CH11
        list.add(new Menu("CH011_1", "Cơm chiên trái thơm", 97900, "https://images.foody.vn/res/g1/865/s120x120/2018918153857-com-chien-trai-thom_resiez.jpg"));
        list.add(new Menu("CH011_2", "Mì Quảng", 86900, "https://images.foody.vn/res/g1/865/s120x120/201891815403-my-quang-2_resiez.jpg"));
        list.add(new Menu("CH011_3", "Cà ri Organic", 97900, "https://images.foody.vn/res/g1/865/s120x120/2018918153634-ca-ri_resiez.jpg"));
        list.add(new Menu("CH011_4", "Mì xào giòn", 97900, "https://images.foody.vn/res/g1/865/s120x120/2018918153949-mi-xao-gion_resiez.jpg"));
        list.add(new Menu("CH011_5", "Cơm chiên Organic", 97900, "https://images.foody.vn/res/g1/865/s120x120/2018918153837-com-chien-organic_resiez.jpg"));

        //CH12
        list.add(new Menu("CH012_1", "Set Combo Duyên", 330000, "https://images.foody.vn/res/g10/94523/s120x120/ce5dbb4b-a3e7-46bd-9066-58f86f1a5b58.jpg"));
        list.add(new Menu("CH012_2", "Set Combo Sen", 310000, "https://images.foody.vn/res/g10/94523/s120x120/9f59e2ac-3027-43f0-14bf-98fea3f053eb.jpg"));
        list.add(new Menu("CH012_3", "Đậu hũ rang muối", 49000, "https://images.foody.vn/res/g10/94523/s120x120/2017127164527-dau-hu-rang-muoi.jpg"));
        list.add(new Menu("CH012_4", "Chả giò phô mai", 64000, "https://images.foody.vn/res/g10/94523/s120x120/2017127163627-cha-ram-an-duyen.jpg"));
        list.add(new Menu("CH012_5", "Chả ram An Duyên", 64000, "https://images.foody.vn/res/g10/94523/s120x120/2017127163627-cha-ram-an-duyen.jpg"));

        //CH13
        list.add(new Menu("CH013_1", "Nấm sốt cuộn giấy bạc", 80000, "https://images.foody.vn/res/g73/726458/s120x120/2505d276-9879-4a82-ad94-bfbea23650e4.jpg"));
        list.add(new Menu("CH013_2", "Kimbap Chay", 50000, "https://images.foody.vn/res/g73/726458/s120x120/79d5cebc-0249-4a58-9489-3f22a4d477fc.jpg"));
        list.add(new Menu("CH013_3", "Đậu hủ chiên sả ớt", 50000, "https://images.foody.vn/res/g73/726458/s120x120/a5384bcf-3b13-4556-ac5f-4bedaafd70eb.jpg"));
        list.add(new Menu("CH013_4", "Đậu hủ kho nấm", 50000, "https://images.foody.vn/res/g73/726458/s120x120/63bb53a5-e532-43ef-be2f-4aeb922f13ad.jpg"));
        list.add(new Menu("CH013_5", "Đậu Hủ Tứ Xuyên", 60000, "https://images.foody.vn/res/g73/726458/s120x120/c0fff766-8803-4706-b21a-b982e405ea03.jpg"));

        //CH14
        list.add(new Menu("CH014_1", "Mì vịt tiềm Vegan", 68000, "https://images.foody.vn/res/g73/723086/s120x120/39f83357-3b26-47ca-61ae-8aff1b781c96.jpg"));
        list.add(new Menu("CH014_2", "Mì nấm tươi", 63000, "https://images.foody.vn/res/g73/723086/s120x120/a2a350af-a2bc-46fe-debf-8d51f6696aac.jpg"));
        list.add(new Menu("CH014_3", "Phở Vegan", 68000, "https://images.foody.vn/res/g73/723086/s120x120/577b4851-95c7-4543-7d4a-8d288c897428.jpg"));
        list.add(new Menu("CH014_4", "Nem vuông", 83000, "https://images.foody.vn/res/g73/723086/s120x120/244688d9-a469-4a48-cfe0-e8391098cd43.jpg"));
        list.add(new Menu("CH014_5", "Hương biển", 82000, "https://images.foody.vn/res/g73/723086/s120x120/b7e0ab30-21fa-47d1-95d0-53d8c95b4448.jpg"));

        //CH15
        list.add(new Menu("CH015_1", "Bún Thái", 44000, "https://images.foody.vn/res/g6/50609/s120x120/201897105112-bun-thai-chay.jpg"));
        list.add(new Menu("CH015_2", "Hủ tiếu sa tế", 44000, "https://images.foody.vn/res/g6/50609/s120x120/201897105238-hu-tieu-sa-te.jpg"));
        list.add(new Menu("CH015_3", "Hủ tiếu Nam Vang nước", 40000, "https://images.foody.vn/res/g6/50609/s120x120/201897105227-hu-tieu-nam-vang-nuoc.jpg"));
        list.add(new Menu("CH015_4", "Bún chả giò", 42000, "https://images.foody.vn/res/g6/50609/s120x120/201897104831-bun-cha-gio.jpg"));
        list.add(new Menu("CH015_5", "Hủ tiếu bò viên", 42000, "https://images.foody.vn/res/g6/50609/s120x120/20181113153918-hu-tieu-bo-vien-chay.jpg"));

        //CH16
        list.add(new Menu("CH016_1", "Combo K-Share HDB", 189000, "https://images.foody.vn/res/g1/4213/s120x120/61904953-3401-4e90-b65d-3afbb2964e26.jpg"));
        list.add(new Menu("CH016_2", "Combo cơm gà nướng kim chi B", 89000, "https://images.foody.vn/res/g1/4213/s120x120/6ec07f98-4c6d-41db-be02-95c2a652109f.jpg"));
        list.add(new Menu("CH016_3", "02 miếng gà sốt Hàn phô mai Suchii", 78000, "https://images.foody.vn/res/g1/4213/s120x120/3253f4c8-1845-4f2e-b8d5-8bd503e2ba71.jpg"));
        list.add(new Menu("CH016_4", "Combo Cơm A", 69000, "https://images.foody.vn/res/g1/4213/s120x120/dc84631b-4689-4d2f-f17e-e605eeeed4f2.jpg"));
        list.add(new Menu("CH016_5", "Burger Gà Quay Flava", 47000, "https://images.foody.vn/res/g1/4213/s120x120/4d65e4d5-51bf-4ec2-888b-50d545d891b7.jpg"));

        //CH17
        list.add(new Menu("CH017_1", "Salad B", 116000, "https://images.foody.vn/res/g1/4030/s120x120/e95b3f79-dbd5-4b1e-b12f-02f62767059e.jpg"));
        list.add(new Menu("CH017_2", "HQ Chicken (Regular)", 44000, "https://images.foody.vn/res/g1/4030/s120x120/eff4b3e1-6818-4c2c-88d7-a0af81872b1c.jpg"));
        list.add(new Menu("CH017_3", "Tôm chiên lớn", 79200, "https://images.foody.vn/res/g1/4030/s120x120/ec3705d6-8585-45af-9121-a70662db0794.jpg"));
        list.add(new Menu("CH017_4", "Pizza hải sản", 156000, "https://images.foody.vn/res/g1/4030/s120x120/foody-pizza%20h%e1%ba%a3i%20s%e1%ba%a3n-635889901104753970.jpg"));
        list.add(new Menu("CH017_5", "Mì Ý sốt bò", 96000, "https://images.foody.vn/res/g1/4030/s120x120/foody-m%c3%ac%20%c3%9d%20s%e1%bb%91t%20b%c3%b2-635889915027778425.jpg"));

        //CH18
        list.add(new Menu("CH018_1", "Salad cá ngừ", 30000, "https://images.foody.vn/res/g26/258535/s120x120/5bddcf92-a957-47c6-a418-32d114ea5d21.jpg"));
        list.add(new Menu("CH018_2", "Sandwich bò trứng phô mai", 35000, "https://images.foody.vn/res/g26/258535/s120x120/201892491855-327f0111-74e6-48e0-8563-9f341e49e0f0.jpg"));
        list.add(new Menu("CH018_3", "Sandwich bò trứng", 30000, "https://images.foody.vn/res/g26/258535/s120x120/2018923155819-775ac5d0-6836-4c4a-a069-6e3e4fb268cd.jpg"));
        list.add(new Menu("CH018_4", "Sandwich bò phô mai", 30000, "https://images.foody.vn/res/g26/258535/s120x120/d66d3c39-6d87-45a4-8cdc-ad9cfc62cb2a.jpg"));
        list.add(new Menu("CH018_5", "Sandwich bò tôm", 40000, "https://images.foody.vn/res/g26/258535/s120x120/f1fd41fa-8ae0-420e-abba-9828a9d1105d.jpg"));

        //CH19
        list.add(new Menu("CH019_1", "Grilled steak salad", 187000, "https://images.foody.vn/res/g14/130222/s120x120/20184216429-grilled-steak-salad.jpg"));
        list.add(new Menu("CH019_2", "Chicken ceasar salad", 176000, "https://images.foody.vn/res/g14/130222/s120x120/20184216149-caesar-salad.jpg"));
        list.add(new Menu("CH019_3", "Feta cheese salad", 121000, "https://images.foody.vn/res/g14/130222/s120x120/798f6be0-2974-4ef6-a338-f9db74ba42a4.jpg"));
        list.add(new Menu("CH019_4", "Shrimp fruit salad", 198000, "https://images.foody.vn/res/g14/130222/s120x120/20184216643-shrimp-fruit-salad.jpg"));
        list.add(new Menu("CH019_5", "Smoked salmon salad", 198000, "https://images.foody.vn/res/g14/130222/s120x120/20184216652-smoked-salmon-salad-1.jpg"));

        //CH20
        list.add(new Menu("CH020_1", "Caesar Salad", 136500, "https://images.foody.vn/res/g1/5100/s120x120/2018910111742-a9-caesar-130k.jpg"));
        list.add(new Menu("CH020_2", "Bruschetta Aglio", 84000, "https://images.foody.vn/res/g1/5100/s120x120/2018910111639-a3-bruschetta-all_aglio-80k.jpg"));
        list.add(new Menu("CH020_3", "Insalata Mista", 94500, "https://images.foody.vn/res/g1/5100/s120x120/2018910111724-a6-isalata-mista-90k.jpg"));
        list.add(new Menu("CH020_4", "Carpaccio di carne", 189000, "https://images.foody.vn/res/g1/5100/s120x120/2018910111934-a14-carpaccio-di-carne-180k.jpg"));
        list.add(new Menu("CH020_5", "Bruschetta", 94500, "https://images.foody.vn/res/g1/5100/s120x120/c79dc1e1-fde5-45df-a1a4-b9e70b5f6257.jpg"));

        //CH21
        list.add(new Menu("CH021_1", "Kem dừa", 70000, "https://images.foody.vn/res/g1/2107/s120x120/04046b07-9111-4e66-adb6-220eeda2667a.jpg"));
        list.add(new Menu("CH021_2", "Kem socola", 65000, "https://images.foody.vn/res/g1/2107/s120x120/ebbd9be0-e28f-4f37-99f8-b2fb7ace80ae.jpg"));
        list.add(new Menu("CH021_3", "Kem sầu riêng", 65000, "https://images.foody.vn/res/g1/2107/s120x120/6f47a31f-099f-462c-be63-34c952e7b8cb.jpg"));
        list.add(new Menu("CH021_4", "Kem rum nho", 65000, "https://images.foody.vn/res/g1/2107/s120x120/bba628a3-bc1e-45b5-9f06-6ec6a6780ad8.jpg"));
        list.add(new Menu("CH021_5", "Kem khoai môn", 65000, "https://images.foody.vn/res/g1/2107/s120x120/7424055d-ddab-48f8-8e23-b1f4733eb453.jpg"));

        //CH22
        list.add(new Menu("CH022_1", "Kem Bơ Dừa", 27000, "https://images.foody.vn/res/g21/203474/s120x120/fa968587-1e1c-41af-9c72-1489997afa98.jpg"));
        list.add(new Menu("CH022_2", "Kem Bơ Sầu Riêng", 31500, "https://images.foody.vn/res/g21/203474/s120x120/78c8d396-e87a-4ce1-b718-4e6d670eedaa.jpg"));
        list.add(new Menu("CH022_3", "Kem Trái Cây", 27000, "https://images.foody.vn/res/g21/203474/s120x120/0990dcc4-9d9a-422a-93a0-4e60b495e3de.jpg"));
        list.add(new Menu("CH022_4", "Kem Sữa Dừa Sầu Riêng", 27000, "https://images.foody.vn/res/g21/203474/s120x120/bfe57032-6d4e-403d-b412-ea302d3a4a7d.jpg"));
        list.add(new Menu("CH022_5", "Trái Cây Tô 12 Món", 23400, "https://images.foody.vn/res/g21/203474/s120x120/5fc1deb1-3853-49f2-955c-87ea69508d94.jpg"));

        //CH23
        list.add(new Menu("CH023_1", "Kem xôi dừa", 29000, "https://images.foody.vn/res/g9/89627/s120x120/2018122910349-kem-xoi-dua.jpg"));
        list.add(new Menu("CH023_2", "Kem nhãn", 10000, "https://images.foody.vn/res/g9/89627/s120x120/a761f4a8-7eef-4980-fd2c-b8f85feba702.jpg"));
        list.add(new Menu("CH023_3", "Kem bơ", 20000, "https://images.foody.vn/res/g9/89627/s120x120/fd2049a2-6bf2-4330-734b-d7b0b137bdf8.jpg"));
        list.add(new Menu("CH023_4", "Kem chocolate", 10000, "https://images.foody.vn/res/g9/89627/s120x120/ffeeb7c2-98bb-49d2-937e-d31fcea7219d.jpg"));
        list.add(new Menu("CH023_5", "Kem cookies", 10000, "https://images.foody.vn/res/g9/89627/s120x120/7504a3a1-2b92-48cc-a834-6d17a39c04f4.jpg"));

        //CH24
        list.add(new Menu("CH024_1", "Kem nhãn hộp 500g", 70000, "https://images.foody.vn/res/g4/30253/s120x120/2017121517858-kem-nhan-hop-500g-60k.jpg"));
        list.add(new Menu("CH024_2", "Kem sầu riêng hộp 500g", 70000, "https://images.foody.vn/res/g4/30253/s120x120/20171215171155-kem-sau-rieng-hop-500g-60k.jpg"));
        list.add(new Menu("CH024_3", "Kem socola hộp 500g", 70000, "https://images.foody.vn/res/g4/30253/s120x120/2017121517929-kem-socola-500g-60k.jpg"));
        list.add(new Menu("CH024_4", "Kem ổi hộp 500g", 70000, "https://images.foody.vn/res/g4/30253/s120x120/20171215171217-kem-oi-hop-500g-60k.jpg"));
        list.add(new Menu("CH024_5", "Kem chanh dây hộp 500g", 70000, "https://images.foody.vn/res/g4/30253/s120x120/2017121517105-kem-chanh-day-hop-500g-60k.jpg"));

        //CH25
        list.add(new Menu("CH025_1", "Việt quất", 32000, "https://images.foody.vn/res/g96/958233/s120x120/b4962690-5426-4ab5-b46a-35feb5f792b8.jpg"));
        list.add(new Menu("CH025_2", "Kem bạc hà", 32000, "https://images.foody.vn/res/g96/958233/s120x120/951d8150-83ff-4227-ac7a-5dd8df814a3f.jpg"));
        list.add(new Menu("CH025_3", "Kem yaourt trái cây", 45000, "https://images.foody.vn/res/g96/958233/s120x120/2fc0c8f3-b4d5-412c-9221-3050a1791caa.jpg"));
        list.add(new Menu("CH025_4", "Kem coca-cola", 39000, "https://images.foody.vn/res/g96/958233/s120x120/2a5ae2db-3484-4230-9aa3-9a7d50ed4620.jpg"));
        list.add(new Menu("CH025_5", "Cafe kem", 39000, "https://images.foody.vn/res/g96/958233/s120x120/5fb0f119-e64b-4a92-9bc6-b33f094da7bb.jpg"));


        //CH26
        list.add(new Menu("CH026_1", "Cá viên", 4000, "https://images.foody.vn/res/g8/72852/s120x120/f254d9fb-d50a-440a-8422-539bd28927ed.jpg"));
        list.add(new Menu("CH026_2", "Bò viên", 5000, "https://images.foody.vn/res/g8/72852/s120x120/2017112115339-c2.jpg"));
        list.add(new Menu("CH026_3", "Trứng cút", 5000, "https://images.foody.vn/res/g8/72852/s120x120/53e9a080-5dd4-40bc-bc4d-df1ae025f35a.jpg"));
        list.add(new Menu("CH026_4", "Phô mai viên", 6000, "https://images.foody.vn/res/g8/72852/s120x120/c93244fe-6395-4a59-bac0-6fefabb34df7.jpg"));
        list.add(new Menu("CH026_5", "Xúc xích hồ lô", 8000, "https://images.foody.vn/res/g8/72852/s120x120/2017117102753-c2.jpg"));


        //CH27
        list.add(new Menu("CH027_1", "Sữa tươi chiên", 5000, "https://images.foody.vn/res/g24/238856/s120x120/c59cffe1-4506-4dc1-9ca3-953c632617db.jpg"));
        list.add(new Menu("CH027_2", "Cá viên", 4500, "https://images.foody.vn/res/g24/238856/s120x120/c51df7d1-a425-4f61-8f91-6e5598a58e57.jpg"));
        list.add(new Menu("CH027_3", "Trứng cút nướng thập cẩm", 12000, "https://images.foody.vn/res/g24/238856/s120x120/fe47facd-20f3-46aa-858f-be9229942359.jpg"));
        list.add(new Menu("CH027_4", "Bánh mì nướng sa tế phô mai ", 15000, "https://images.foody.vn/res/g24/238856/s120x120/bdf88b01-bca6-4ee5-a2c5-61967f8354c3.jpg"));
        list.add(new Menu("CH027_5", "Hải sản viên sốt mayonnaise", 7000, "https://images.foody.vn/res/g24/238856/s120x120/89b1a65c-ebe4-4d3e-8a0d-e533897bedc1.jpg"));

        //CH28
        list.add(new Menu("CH028_1", "Snack chả giò tôm mini 100gr", 80000, "https://images.foody.vn/res/g25/244522/s120x120/f1806c65-f5bc-465c-9e30-8b1c1951e4c8.jpg"));
        list.add(new Menu("CH028_2", "Bắp rim khô bò 100gr", 50000, "https://images.foody.vn/res/g25/244522/s120x120/1dfb17bc-fd1a-4595-a276-07d5720ed545.jpg"));
        list.add(new Menu("CH028_3", "Thịt nướng (500gr)", 120000, "https://images.foody.vn/res/g25/244522/s120x120/152aa5d8-6c85-4d01-9aed-563f7a6c9358.jpg"));
        list.add(new Menu("CH028_4", "Rong biển cháy tỏi 100gr", 90000, "https://images.foody.vn/res/g25/244522/s120x120/98707876-7e51-4dc7-8b60-afe062bc6a2e.jpg"));
        list.add(new Menu("CH028_5", "Gà xé lá chanh 100gr", 50000, "https://images.foody.vn/res/g25/244522/s120x120/87001565-c7cf-4cd5-9a3d-d8f3fdb35110.jpg"));

        //CH29
        list.add(new Menu("CH029_1", "Combo Cá Bò Tôm 20 viên", 25000, "https://images.foody.vn/res/g93/923209/s120x120/af761c72-642c-4321-ba78-873f7859b53a.jpg"));
        list.add(new Menu("CH029_2", "Nem Chua Rán Hà Nội", 30000, "https://images.foody.vn/res/g93/923209/s120x120/073cacb8-8d49-4dc7-ba72-91ea5483fa83.jpg"));
        list.add(new Menu("CH029_3", "Hải sản mayo", 25000, "https://images.foody.vn/res/g93/923209/s120x120/9c2fc967-cca3-48d4-b5c6-a4a9353db180.jpg"));
        list.add(new Menu("CH029_4", "Xúc xích Đức", 25000, "https://images.foody.vn/res/g93/923209/s120x120/4b15c9b9-1aab-49ec-80b3-cec46a867eac.jpg"));
        list.add(new Menu("CH029_5", "Phô Mai Que", 25000, "https://images.foody.vn/res/g93/923209/s120x120/d102b2fc-d0ab-48b1-be91-cbaf070d833e.jpg"));

        //CH30
        list.add(new Menu("CH030_1", "Cơm cháy kho quẹt", 45000, "https://images.foody.vn/res/g74/736879/s120x120/3765a0f4-c6a9-4442-ac4e-6afacb59ea7f.jpg"));
        list.add(new Menu("CH030_2", "Rau luộc", 20000, "https://images.foody.vn/res/g74/736879/s120x120/213e22a3-4641-45dd-a3ef-daf0c22f6409.jpg"));
        list.add(new Menu("CH030_3", "Cơm cháy mỡ hành - chà bông", 45000, "https://images.foody.vn/res/g74/736879/s120x120/7f43d381-9f4a-4a94-be9a-b97801d1ddaa.jpg"));
        list.add(new Menu("CH030_4", "Cơm cháy thêm", 20000, "https://images.foody.vn/res/g74/736879/s120x120/e90cb81c-536a-413a-9932-a525170c1d70.jpg"));
        list.add(new Menu("CH030_5", "Cơm cháy thịt ba chỉ xào mắm ruốc", 45000, "https://images.foody.vn/res/g74/736879/s120x120/930aa38e-a548-4908-81dd-2235335fc068.jpg"));

        //CH31
        list.add(new Menu("CH031_1", "Tiramisu Tam giác", 35000, "https://images.foody.vn/res/g77/767168/s120x120/d942b7b1-38d7-4da2-2d3c-d72b53dbe9de.jpg"));
        list.add(new Menu("CH031_2", "Tiramisu ly", 35000, "https://images.foody.vn/res/g77/767168/s120x120/8c1d8dd9-3804-48a2-afc3-987102fa0a14.jpg"));
        list.add(new Menu("CH031_3", "Rau Câu Flancheese Hoa Nổi Nhỏ", 35000, "https://images.foody.vn/res/g77/767168/s120x120/ed44c008-19a2-46a1-1066-684842ae5672.jpg"));
        list.add(new Menu("CH031_4", "Bánh Rau Câu Flancheese Hoa Nổi lớn", 360000, "https://images.foody.vn/res/g77/767168/s120x120/b7adbea4-27cd-4280-2ccb-d6810eb472cf.jpg"));
        list.add(new Menu("CH031_5", "Bánh mì phô mai chảy size nhỏ", 12000, "https://images.foody.vn/res/g77/767168/s120x120/7e075c39-2f45-4a27-5adf-2f64666b6aee.jpg"));

        //CH32
        list.add(new Menu("CH032_1", "Bánh bông lan trứng muối nhỏ", 10000, "https://images.foody.vn/res/g14/131234/s120x120/ab5b0f30-50fb-46a0-b474-52e60724a73d.jpg"));
        list.add(new Menu("CH032_2", "Bánh ngọt các loại", 9000, "https://images.foody.vn/res/g14/131234/s120x120/f3ab96a9-cbcc-4507-a9f1-6d6d37f65111.jpg"));
        list.add(new Menu("CH032_3", "Bông lan phô mai nướng", 50000, "https://images.foody.vn/res/g14/131234/s120x120/6611009f-a92b-4735-bf3e-bd2edab8e0be.jpg"));
        list.add(new Menu("CH032_4", "Bánh su que", 30000, "https://images.foody.vn/res/g14/131234/s120x120/a59eb509-8bb5-4614-811a-511932402e99.jpg"));
        list.add(new Menu("CH032_5", "Sandwich viết quất", 37000, "https://images.foody.vn/res/g14/131234/s120x120/5d3392e8-fa90-4760-bb62-1b15aa9fa61f.jpg"));

        //CH33
        list.add(new Menu("CH033_1", "Bánh trà đào (12cm x 14cm)", 310000, "https://images.foody.vn/res/g13/129958/s120x120/6bf0467c-9b07-46e8-015a-c530c368aecc.jpg"));
        list.add(new Menu("CH033_2", "Bánh bắp (12cm x 14cm)", 310000, "https://images.foody.vn/res/g13/129958/s120x120/6b8cfb58-389b-424f-c7b4-997b7e335c19.jpg"));
        list.add(new Menu("CH033_3", "Bánh dâu tây (14cm x 18cm)", 630000, "https://images.foody.vn/res/g13/129958/s120x120/78d78699-ce40-4ac1-78c6-36396c08c831.jpg"));
        list.add(new Menu("CH033_4", "Bánh espresso (12cm x 14cm)", 310000, "https://images.foody.vn/res/g13/129958/s120x120/a1b69ce3-979b-47ad-f4d6-6c4578e6359d.jpg"));
        list.add(new Menu("CH033_5", "Bánh matcha (12cm x 14cm)", 310000, "https://images.foody.vn/res/g13/129958/s120x120/e92a889f-a6f6-48f1-aa4c-9f23bffd2bf5.jpg"));

        //CH34
        list.add(new Menu("CH034_1", "Original Cheesecake Slice", 59000, "https://images.foody.vn/res/g5/44454/s120x120/a17345f6-19d2-4448-9d97-3562ccb6d3f2.jpg"));
        list.add(new Menu("CH034_2", "Passion Fruit Cheesecake Slice", 59000, "https://images.foody.vn/res/g5/44454/s120x120/360379ab-6f12-449f-83d4-27d552bfe2e3.jpg"));
        list.add(new Menu("CH034_3", "Oreo Cheesecake Slice", 59000, "https://images.foody.vn/res/g5/44454/s120x120/7698d8c2-9131-47e1-b693-5d72f9afcf77.jpg"));
        list.add(new Menu("CH034_4", "Apple Crumble Cheesecake", 69000, "https://images.foody.vn/res/g5/44454/s120x120/82859154-155c-43e4-8981-838667901603.jpg"));
        list.add(new Menu("CH034_5", "Red Velvet Cheesecake", 69000, "https://images.foody.vn/res/g5/44454/s120x120/a16adc6f-5854-4400-8278-14d447de190b.jpg"));

        //CH35
        list.add(new Menu("CH035_1", "Sweet Corn Cake", 260000, "https://images.foody.vn/res/g32/315622/s120x120/20181213171323-banh-bap.jpg"));
        list.add(new Menu("CH035_2", "Bánh kem Kiosi", 240000, "https://images.foody.vn/res/g32/315622/s120x120/20181213165735-kiosi-2.jpg"));
        list.add(new Menu("CH035_3", "Fresh Pine Cake", 210000, "https://images.foody.vn/res/g32/315622/s120x120/2018121316510-banh-thom.jpg"));
        list.add(new Menu("CH035_4", "Bánh choux", 30000, "https://images.foody.vn/res/g32/315622/s120x120/2018118164615-ch.jpg"));
        list.add(new Menu("CH035_5", "Combo 6 bánh teabreak", 60000, "https://images.foody.vn/res/g32/315622/s120x120/2018121317412-teabreak-02.jpg"));


        //add to firebase
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i).getMamonan().substring(0, list.get(i).getMamonan().indexOf("_"));
            mData.child("Menu").child(key).child(list.get(i).getMamonan()).setValue(list.get(i));
        }
    }

}
