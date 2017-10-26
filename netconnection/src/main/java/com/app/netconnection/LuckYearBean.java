package com.app.netconnection;

import java.util.List;

/**
 * Created by merbng on 2017/10/26.
 */

public class LuckYearBean {

    @Override
    public String toString() {
        return "LuckBean{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", year=" + year +
                ", mima=" + mima +
                ", luckeyStone='" + luckeyStone + '\'' +
                ", future='" + future + '\'' +
                ", resultcode='" + resultcode + '\'' +
                ", error_code=" + error_code +
                ", career=" + career +
                ", love=" + love +
                ", health=" + health +
                ", finance=" + finance +
                '}';
    }

    /**
     * name : 双鱼座
     * date : 2017年
     * year : 2017
     * mima : {"info":"携手共进 事业冲顶","text":["对大多数双鱼座而言，未来一年将是忙碌却充实，磕磕碰碰中触碰天际的一年。土星在双鱼座的事业宫已经停留了将近2年时间，也让双鱼们吃了不少苦头，原来很轻松的工作变得要求高高，或是被委以重任必须每一件事都全身心投入努力才可能看到成绩，但也是你们实现自我价值和社会地位的重要环节。尤其来到2017年，土天冲持续作用令事业宫与财帛宫联动，使得你们的付出很容易转换成物质回报。命主星木星在危机宫却为事业宫提供支持，很多双鱼座会担任起救火队长的工作，成为支援队伍中的中流砥柱，或是依靠自己良好的人脉为他人铺路，同时获得正反馈实现个人职位和价值的提升，并有望突破阶层的天花板，进入更加高大上的层次，实现自我价值的飞跃。"]}
     * career : ["未来一年将是双鱼座事业持续走高，职场得偿所愿的一年。很多双鱼座在过去两年都选择了一条充满挑战的职场之路，只希望能够实现心中的梦想。而在经历过土星考验的两年后，那些真正努力的双鱼座将在今年看到显著成果。整体而言，从年后开始一直到8月中旬都颇多波折，直到8月底开始至11月上旬将是一年中事业发展最为顺遂的时段，不论是求职还是升职都将事半功倍。独立创业的双鱼座将有望彻底确定自身的行业地位，并将公司和产品打造成型获得市场认可，实现资金上的正循环，部分创业者也可以考虑接受他人的投资扩大影响，把市场做大以寻求更高利润回报。打工族会有较高概率升职成功，或是跳槽到自己心仪的公司和岗位，与他人合作时更易获得八方支持，即便需要加班解决问题，也都能获得不错的物质补贴。自雇业者也有很多不错的上升机会，但切忌吃独食，必须学会与人分享利润才可能拿下优质项目。"]
     * love : ["对大多数双鱼座来说，此前两年的多次双鱼处女轴线日月食已经让你们的感情找到了归宿，因此在整个2017年的前三季度，大多数双鱼座的感情还将循着既定轨迹继续运转。单身双鱼座可能会将更多精力灌注在工作领域，而相对忽略个人的感情状况，但也很容易在工作场合认识合作方擦出火花，然后不了了之。有伴双鱼座则可能陷入坚持还是放弃的两难纠结之中。命主星在危机宫位让你们的整个生活都处于巨大转折之中，当前对象若已经结婚倒也还能保持现况稳定持续。而另一些未能确定结婚的双鱼座则可能发现因为自身提升太快而与对方渐渐拉开距离，为了弥补双方鸿沟，不仅要消耗精力，更是燃烧荷包，但又很难下定决心分手，不免成为纠结的困扰。等待10月10日木星进入天蝎座后，你的人生路径将更加清晰，也会明白自己到底应该选择怎样的人，或是碰到让自己更加迷恋的对象，最终做出决定。"]
     * health : ["今年身体状况一般，可能会出现过劳的现象。是减肥的好时机，适合修生养息。"]
     * finance : ["今年双鱼们的收入主要来源于正财的工作得财，或是源于偏财的中介抽成，收入还算稳步上升，但花钱的地方却也不少，总体只能算是收支平衡。双鱼座全年的正财运都相对稳定，只要努力工作就能获得正比收入，部分双鱼座可能要给另一半花上不少钱，但心情还算不错，总体也可以说是甜蜜的支出。另一些双鱼座则可能通过帮人解决麻烦，或是代购代办事务而获得他人的认可和物质回报，因此良好的人脉资源将是今年偏财收入的必须保障。虽然收入稳步增长，但今年双鱼们也会有很多额外的财务支出，或是因为突如其来的兴趣想去学习一些技能（虽然过后未必用的上），或是因为恋爱和小孩买上一堆其实没什么用的东西，相对也很难存下钱来。8月底之后会有更多机会因为加薪提升收入。春节假期结束之前以及8月底、9月份都是偏财收入较好的时段。3月中到4月中是相对容易破财的时段，尤其注意手中的电子设备和通讯物品。6月到7月中旬则容易因为恋爱和小孩额外支出，也是容易发生投资损失的时段，小心为佳。 "]
     * luckeyStone : 青金石
     * future :
     * resultcode : 200
     * error_code : 0
     */

    private String name;
    private String date;
    private int year;
    private MimaBean mima;
    private String luckeyStone;
    private String future;
    private String resultcode;
    private int error_code;
    private List<String> career;
    private List<String> love;
    private List<String> health;
    private List<String> finance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MimaBean getMima() {
        return mima;
    }

    public void setMima(MimaBean mima) {
        this.mima = mima;
    }

    public String getLuckeyStone() {
        return luckeyStone;
    }

    public void setLuckeyStone(String luckeyStone) {
        this.luckeyStone = luckeyStone;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<String> getCareer() {
        return career;
    }

    public void setCareer(List<String> career) {
        this.career = career;
    }

    public List<String> getLove() {
        return love;
    }

    public void setLove(List<String> love) {
        this.love = love;
    }

    public List<String> getHealth() {
        return health;
    }

    public void setHealth(List<String> health) {
        this.health = health;
    }

    public List<String> getFinance() {
        return finance;
    }

    public void setFinance(List<String> finance) {
        this.finance = finance;
    }

    public static class MimaBean {
        /**
         * info : 携手共进 事业冲顶
         * text : ["对大多数双鱼座而言，未来一年将是忙碌却充实，磕磕碰碰中触碰天际的一年。土星在双鱼座的事业宫已经停留了将近2年时间，也让双鱼们吃了不少苦头，原来很轻松的工作变得要求高高，或是被委以重任必须每一件事都全身心投入努力才可能看到成绩，但也是你们实现自我价值和社会地位的重要环节。尤其来到2017年，土天冲持续作用令事业宫与财帛宫联动，使得你们的付出很容易转换成物质回报。命主星木星在危机宫却为事业宫提供支持，很多双鱼座会担任起救火队长的工作，成为支援队伍中的中流砥柱，或是依靠自己良好的人脉为他人铺路，同时获得正反馈实现个人职位和价值的提升，并有望突破阶层的天花板，进入更加高大上的层次，实现自我价值的飞跃。"]
         */

        private String info;
        private List<String> text;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }
    }
}
