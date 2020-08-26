package com.sy;

import com.github.pagehelper.PageInfo;
import com.sy.common.model.BaseResult;
import com.sy.common.model.PageResult;
import com.sy.dsz.model.bank.AccountDetail;
import com.sy.dsz.model.bank.UserAccount;
import com.sy.dsz.model.gouwu.*;
import com.sy.dsz.model.zixun.Affiche;
import com.sy.dsz.model.zixun.Information;
import com.sy.dsz.model.zixun.LeaveMessage;
import com.sy.dsz.service.bank.AccountDetailService;
import com.sy.dsz.service.bank.UserAccountService;
import com.sy.dsz.service.gouwu.*;
import com.sy.dsz.service.zixun.AfficheService;
import com.sy.dsz.service.zixun.InformationService;
import com.sy.dsz.service.zixun.LeaveMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SyshoppingApplicationTests {

    @Autowired
    private OrdersInfoService ordersInfoService;
    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private GoodsPackService goodsPackService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private CartGoodsService cartGoodsService;

    @Test
    public void testGouWu() throws Exception {
        PageInfo<OrdersInfo> allByPage = ordersInfoService.findAllByPage(1, 2);
        List<OrdersInfo> all = allByPage.getList();
        System.out.println(all);
        System.out.println(allByPage.getTotal());

    }

    @Test
    public void testGouWuadd() throws Exception {
        OrdersInfo ordersInfo = new OrdersInfo();
        ordersInfo.setCreateBy("admin");
        ordersInfo.setCreateTime(new Date());
        ordersInfo.setLastUpdateTime(new Date());
        ordersInfo.setStatus(1);
        ordersInfo.setOrderPrice(300.0);
        ordersInfo.setUserId(1);
        ordersInfo.setOrderCode("o003");
        int i = ordersInfoService.add(ordersInfo);
        System.out.println(i);
    }

    @Test
    public void testGouWuDelete() throws Exception {
        int i = ordersInfoService.deleteById(4);
        System.out.println(i);
    }

    @Test
    public void testGouWuGoodsFind() throws Exception {
        PageInfo<GoodsInfo> all = goodsInfoService.findAll(1, 2);
        List<GoodsInfo> list = all.getList();
        System.out.println(list);
        System.out.println(all.getTotal());
    }

    @Test
    public void testGouWuGoodsAdd() throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setCreatedBy("user");
        goodsInfo.setCreateTime(new Date());
        goodsInfo.setGoodsFormat("规格2");
        goodsInfo.setGoodsName("商品2");
        goodsInfo.setGoodsSN("g002");
        goodsInfo.setLastUpdateTime(new Date());
        goodsInfo.setMarketPrice(289.0);
        goodsInfo.setNote("这是商品2");
        goodsInfo.setNum(287);
        goodsInfo.setRealPrice(123.0);
        goodsInfo.setState(1);
        goodsInfo.setUnit("sy商品");
        int add = goodsInfoService.add(goodsInfo);
        System.out.println(add);
    }

    @Test
    public void testGouWuGoodsUpdate() throws Exception {
        GoodsInfo goodsInfo = goodsInfoService.findById(22);

        System.out.println(goodsInfo);
        goodsInfo.setGoodsSN("good3");
        int modify = goodsInfoService.modify(goodsInfo);
        System.out.println(modify);
    }

    @Test
    public void testGouWuPackAdd() throws Exception {
        GoodsPack goodsPack = new GoodsPack();
        goodsPack.setCreateTime(new Date());
        goodsPack.setGoodsPackCode("pack003");
        goodsPack.setGoodsPackName("套餐3");
        goodsPack.setLastUpdateTime(new Date());
        goodsPack.setNote("这是套餐3");
        goodsPack.setNum(99);
        goodsPack.setState(1);
        goodsPack.setTotalPrice(230.0);
        goodsPack.setTypeId(1);
        goodsPack.setTypeName("类型3");
        goodsPack.setCreatedBy("admin");
        int add = goodsPackService.add(goodsPack);
        System.out.println(add);
    }

    @Test
    public void testGouWuPackFind() throws Exception {
        PageInfo<GoodsPack> all = goodsPackService.findAll(1, 2);
        List<GoodsPack> list = all.getList();
        System.out.println(list);
        System.out.println(all.getTotal());
    }

    @Test
    public void testGouWuPackUpdate() throws Exception {
        GoodsPack goodsPack = goodsPackService.findById(22);

        System.out.println(goodsPack);
        goodsPack.setGoodsPackName("套餐2");
        goodsPack.setGoodsPackCode("packb");
        int modify = goodsPackService.modify(goodsPack);
        System.out.println(modify);
    }

    @Test
    public void testGouWuPackDelete() throws Exception {
        int i = goodsPackService.deleteById(23);
        System.out.println(i);
    }

    @Test
    public void testGouWuCartAdd() throws Exception {
        ShopCart shopCart = new ShopCart();
        shopCart.setCreateBy("user");
        shopCart.setCreateTime(new Date());
        shopCart.setLastUpdateTime(new Date());
        shopCart.setTotalPrice(234.0);
        shopCart.setUserId(1);

        int add = shopCartService.add(shopCart);
        System.out.println(add);
    }

    @Test
    public void testGouWuCartFind() throws Exception {
        PageInfo<ShopCart> all = shopCartService.findAll(1, 2);
        List<ShopCart> list = all.getList();
        System.out.println(list);
        System.out.println(all.getTotal());
    }

    @Test
    public void testGouWuCartUpdate() throws Exception {
        ShopCart shopCart = shopCartService.findById(3);

        System.out.println(shopCart.getTotalPrice());
        shopCart.setTotalPrice(300.0);
        int modify = shopCartService.modify(shopCart);
        System.out.println(modify);
    }

    @Test
    public void testGouWuCartDelete() throws Exception {
        int i = shopCartService.deleteById(3);
        System.out.println(i);
    }


    @Test
    public void testGouWuCartGoodsAdd() throws Exception {
        CartGoods cartGoods = new CartGoods();
        cartGoods.setGoodsId(1);
        cartGoods.setGoodsName("商品3");
        cartGoods.setGoodsPrice(234.9);
        cartGoods.setCartId(2);
        cartGoods.setFlag(1);
        cartGoods.setCreateBy("admin");
        cartGoods.setCreateTime(new Date());
        cartGoods.setLastUpdateTime(new Date());
        cartGoods.setGoodsNum(21);
        int add = cartGoodsService.add(cartGoods);
        System.out.println(add);
    }

    @Test
    public void testGouWuCartGoodsFind() throws Exception {
        PageInfo<CartGoods> all = cartGoodsService.findAll(1, 2);
        List<CartGoods> list = all.getList();
        System.out.println(list);
        System.out.println(all.getTotal());
    }

    @Test
    public void testGouWuCartGoodsUpdate() throws Exception {

    }

    @Test
    public void testGouWuCartGoodsDelete() throws Exception {
        int i = cartGoodsService.deleteById(3);
        System.out.println(i);
    }


    //.................bank

    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private UserAccountService userAccountService;


    @Test
    public void testBankDetail_Add()throws Exception{
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAccountDate(new Date());
        accountDetail.setAccountId(3);
        accountDetail.setMoneyIn(300.0);
        accountDetail.setMoneyOut(3.0);
        accountDetail.setOtherAccountId(1);
        accountDetail.setType(1);
        accountDetailService.addAccountDetail(accountDetail);
    }


    @Test
    public void testBankDetail_FindByAccountId()throws Exception{
        BaseResult baseResult = new BaseResult();
        baseResult.setPage(1);
        baseResult.setLimit(2);
        PageInfo<AccountDetail> byAccountId = accountDetailService.findByAccountId(1,baseResult);
        System.out.println(byAccountId);
    }
    @Test
    public void testBankDetail_Modify()throws Exception{


    }

    @Test
    public void testBankUserAccount_add()throws Exception{
        UserAccount userAccount = new UserAccount();
        userAccount.setBalance(500.0);
        userAccount.setCreateBy("admin");
        userAccount.setCreateTime(new Date());
        userAccount.setLastUpdateTime(new Date());
        userAccount.setStatus(1);
        userAccount.setUserId(1);
        userAccountService.addUserAccount(userAccount);
    }

    @Test
    public void testBankUserAccount_FindByUserId()throws Exception{
        UserAccount byUserId = userAccountService.findByUserId(1);
        System.out.println(byUserId.getId());
    }

    @Test
    public void testBankUserAccount_FindUserSell()throws Exception{
        Integer userSell = userAccountService.findUserSell(1, 1, 2);
        System.out.println(userSell);
    }

    @Test
    public void testBankUserAccount_modifyUserAccount()throws Exception{
        userAccountService.modifyUserAccount(20.0,new Date(),1);
        userAccountService.modifyUserBalance(20.0,new Date(),2);
    }


    //...................咨询

    @Autowired
    private AfficheService afficheService;

    @Test
    public void testzixun_afficheAdd()throws Exception{
        Affiche affiche = new Affiche();
        affiche.setContent("这是公告4");
        affiche.setPublishTime(new Date(System.currentTimeMillis()));
        affiche.setStartTime(new Date(System.currentTimeMillis()));
        affiche.setPublisher("admin");
        affiche.setEndTime(new Date(System.currentTimeMillis()));

        affiche.setTitle("公告4");
        afficheService.addAffiche(affiche);
    }

    @Test
    public void testzixun_afficheFind()throws Exception{
        BaseResult pageResult = new BaseResult();
        pageResult.setPage(1);
        pageResult.setLimit(2);

        PageInfo allByPage = afficheService.findAllByPage(pageResult);
        System.out.println(allByPage);
        System.out.println("------------");
        Affiche affiche = new Affiche();
        affiche.setTitle("3");
        PageInfo byTitle = afficheService.findByTitle(affiche, pageResult);
        System.out.println(byTitle);
        System.out.println("------------");

        Affiche oneById = afficheService.findOneById(42);
        System.out.println(oneById.getId());
    }


    @Test
    public void testzixun_afficheModify()throws Exception{
        Affiche oneById = afficheService.findOneById(42);
        oneById.setContent("这是修改的公告2......");
        afficheService.modifyAffiche(oneById);
    }
    @Test
    public void testzixun_afficheRemove()throws Exception{
        Integer integer = afficheService.removeAffiche(44);
        System.out.println(integer);
    }


    @Autowired
    private InformationService informationService;
    @Test
    public void testzixun_InformationAdd()throws Exception{
        Information information = new Information();
        information.setState(1);
        information.setFileName("b.txt");
        information.setFilePath("/b.txt");
        information.setFileSize(10.0);
        information.setPublisher("admin");
        information.setPublishTime(new Date());
        information.setTypeId(1);
        information.setTypeName("图片");
        information.setUploadTime(new Date());
        information.setContent("上传第er个文件");
        information.setTitle("传图");
        informationService.addInformation(information);

    }

    @Test
    public void testzixun_informationFind()throws Exception{
        BaseResult baseResult = new BaseResult();
        baseResult.setLimit(2);
        baseResult.setPage(1);

        Information informationById = informationService.findInformationById(41);
        System.out.println(informationById);
        System.out.println("------------");

        PageInfo all = informationService.findAll(baseResult);
        System.out.println(all);
        System.out.println("------------");

        Information information = new Information();
        information.setTitle("a");
        PageInfo byTitle = informationService.findByTitle(information, baseResult);
        System.out.println(byTitle);
        System.out.println("------------");

    }


    @Test
    public void testzixun_informationModify()throws Exception{
        Information information = informationService.findInformationById(41);

        information.setTitle("修改图片a");

        Integer integer = informationService.modifyInformation(information);
        System.out.println(integer);
    }
    @Test
    public void testzixun_informationRemove()throws Exception{
        Information information = informationService.findInformationById(41);
        Integer integer = informationService.removeInformation(information);
        System.out.println(integer);
    }

    @Autowired
    private LeaveMessageService leaveMessageService;
    @Test
    public void testzixun_leaveMessageADd()throws Exception{
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setCreateBy("admin");
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setMessageCode("a124");
        leaveMessage.setState(1);
        leaveMessage.setMessageContent("留言2");
        leaveMessage.setMessageTitle("标题2");
        leaveMessageService.addLeaveMessage(leaveMessage);
    }

    @Test
    public void testzixun_leaveMessageFind()throws Exception{
        BaseResult baseResult = new BaseResult();
        baseResult.setLimit(2);
        baseResult.setPage(1);
        PageInfo pageInfo = leaveMessageService.findByTitle(baseResult, "标题");
        System.out.println(pageInfo);
        System.out.println("------------");
    }

}

