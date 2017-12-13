package uw.httpclient.http.xml.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import uw.httpclient.http.xml.vo.LvmamaPriceInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Lvmama的库存和价格信息Vo[带错误节点]
 * @author liliang
 * @since 2017/12/13
 */
@JacksonXmlRootElement(localName = "response")
public class LvmamaPriceInfoVoErrorVo {

    /**
     *
     */
    private LvmamaPriceInfoVo.State state = new LvmamaPriceInfoVo.State();

    /**
     *
     */
    private LvmamaPriceInfoVo.PriceList priceList = new LvmamaPriceInfoVo.PriceList();


    /**
     * 不分销的商品 id
     */
    private String notDistGoodsIds;

    public void setState(LvmamaPriceInfoVo.State state) {
        this.state = state;
    }

    @JacksonXmlProperty(localName="state")
    public LvmamaPriceInfoVo.State getState (){
        return this.state;
    }

    public void setPriceList(LvmamaPriceInfoVo.PriceList priceList) {
        this.priceList = priceList;
    }

    @JacksonXmlProperty(localName="priceList")
    public LvmamaPriceInfoVo.PriceList getPriceList (){
        return this.priceList;
    }

    public String getNotDistGoodsIds() {
        return notDistGoodsIds;
    }

    public void setNotDistGoodsIds(String notDistGoodsIds) {
        this.notDistGoodsIds = notDistGoodsIds;
    }

    @JacksonXmlRootElement(localName = "state")
    public static class State {
        /**
         * 1000
         */
        private String code;

        /**
         * 接口访问成功
         */
        private String solution;

        /**
         * 成功
         */
        private String message;

        /**
         * 错误处理
         */
        private LvmamaPriceInfoVo.SubErrors subErrors = new LvmamaPriceInfoVo.SubErrors();

        public void setCode(String code) {
            this.code = code;
        }

        @JacksonXmlProperty(localName="code")
        public String getCode (){
            return this.code;
        }

        public void setSolution(String solution) {
            this.solution = solution;
        }

        @JacksonXmlProperty(localName="solution")
        public String getSolution (){
            return this.solution;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @JacksonXmlProperty(localName="message")
        public String getMessage (){
            return this.message;
        }

        public LvmamaPriceInfoVo.SubErrors getSubErrors() {
            return subErrors;
        }

        public void setSubErrors(LvmamaPriceInfoVo.SubErrors subErrors) {
            this.subErrors = subErrors;
        }
    }

    @JacksonXmlRootElement(localName = "subErrors")
    public static class SubErrors {
        /**
         *
         */
        private LvmamaPriceInfoVo.SubError subError = new LvmamaPriceInfoVo.SubError();

        public void setSubError(LvmamaPriceInfoVo.SubError subError) {
            this.subError = subError;
        }

        @JacksonXmlProperty(localName="subError")
        public LvmamaPriceInfoVo.SubError getSubError (){
            return this.subError;
        }

    }

    @JacksonXmlRootElement(localName = "subError")
    public static class SubError {
        /**
         * isv.invalid-paramete:goodsIds
         */
        private String code;

        /**
         * 参数goodsIds无效，格式不对、非法值、越界等
         */
        private String message;

        public void setCode(String code) {
            this.code = code;
        }

        @JacksonXmlProperty(localName="code")
        public String getCode (){
            return this.code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @JacksonXmlProperty(localName="message")
        public String getMessage (){
            return this.message;
        }

    }

    @JacksonXmlRootElement(localName = "price")
    public static class Price {
        /**
         * 2017-12-08
         */
        private String date;

        /**
         * 280
         */
        private double marketPrice;

        /**
         * -1140
         */
        private int aheadHour;

        /**
         * 200
         */
        private double b2bPrice;

        /**
         * 140
         */
        private double sellPrice;

        /**
         * -1
         */
        private int stock;

        public void setDate(String date) {
            this.date = date;
        }

        @JacksonXmlProperty(localName="date")
        public String getDate (){
            return this.date;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        @JacksonXmlProperty(localName="marketPrice")
        public double getMarketPrice (){
            return this.marketPrice;
        }

        public void setAheadHour(int aheadHour) {
            this.aheadHour = aheadHour;
        }

        @JacksonXmlProperty(localName="aheadHour")
        public int getAheadHour (){
            return this.aheadHour;
        }

        public void setB2bPrice(double b2bPrice) {
            this.b2bPrice = b2bPrice;
        }

        @JacksonXmlProperty(localName="b2bPrice")
        public double getB2bPrice (){
            return this.b2bPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        @JacksonXmlProperty(localName="sellPrice")
        public double getSellPrice (){
            return this.sellPrice;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        @JacksonXmlProperty(localName="stock")
        public int getStock (){
            return this.stock;
        }

    }

    @JacksonXmlRootElement(localName = "prices")
    public static class Prices {
        /**
         *
         */
        private List<LvmamaPriceInfoVo.Price> price = new ArrayList<LvmamaPriceInfoVo.Price>();

        public void setPrice(List<LvmamaPriceInfoVo.Price> price) {
            this.price = price;
        }

        @JacksonXmlElementWrapper(useWrapping=false)
        @JacksonXmlProperty(localName="price")
        public List<LvmamaPriceInfoVo.Price> getPrice (){
            return this.price;
        }

    }

    @JacksonXmlRootElement(localName = "goods")
    public static class Goods {
        /**
         * true
         */
        private boolean goodsOnLine;

        /**
         * 979263
         */
        private String goodsId;

        /**
         * 0
         */
        private String commentsCashback;

        /**
         *
         */
        private LvmamaPriceInfoVo.Prices prices = new LvmamaPriceInfoVo.Prices();

        /**
         * VIP票
         */
        private String goodsName;

        public void setGoodsOnLine(boolean goodsOnLine) {
            this.goodsOnLine = goodsOnLine;
        }

        @JacksonXmlProperty(localName="goodsOnLine")
        public boolean getGoodsOnLine (){
            return this.goodsOnLine;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        @JacksonXmlProperty(localName="goodsId")
        public String getGoodsId (){
            return this.goodsId;
        }

        public void setCommentsCashback(String commentsCashback) {
            this.commentsCashback = commentsCashback;
        }

        @JacksonXmlProperty(localName="commentsCashback")
        public String getCommentsCashback (){
            return this.commentsCashback;
        }

        public void setPrices(LvmamaPriceInfoVo.Prices prices) {
            this.prices = prices;
        }

        @JacksonXmlProperty(localName="prices")
        public LvmamaPriceInfoVo.Prices getPrices (){
            return this.prices;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        @JacksonXmlProperty(localName="goodsName")
        public String getGoodsName (){
            return this.goodsName;
        }

    }

    @JacksonXmlRootElement(localName = "goodsList")
    public static class GoodsList {
        /**
         *
         */
        private LvmamaPriceInfoVo.Goods goods = new LvmamaPriceInfoVo.Goods();

        public void setGoods(LvmamaPriceInfoVo.Goods goods) {
            this.goods = goods;
        }

        @JacksonXmlProperty(localName="goods")
        public LvmamaPriceInfoVo.Goods getGoods (){
            return this.goods;
        }

    }

    @JacksonXmlRootElement(localName = "product")
    public static class Product {
        /**
         * 259171
         */
        private String productId;

        /**
         *
         */
        private LvmamaPriceInfoVo.GoodsList goodsList = new LvmamaPriceInfoVo.GoodsList();

        /**
         * true
         */
        private String productStatus;

        public void setProductId(String productId) {
            this.productId = productId;
        }

        @JacksonXmlProperty(localName="productId")
        public String getProductId (){
            return this.productId;
        }

        public void setGoodsList(LvmamaPriceInfoVo.GoodsList goodsList) {
            this.goodsList = goodsList;
        }

        @JacksonXmlProperty(localName="goodsList")
        public LvmamaPriceInfoVo.GoodsList getGoodsList (){
            return this.goodsList;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

        @JacksonXmlProperty(localName="productStatus")
        public String getProductStatus (){
            return this.productStatus;
        }

    }

    @JacksonXmlRootElement(localName = "priceList")
    public static class PriceList {
        /**
         *
         */
        private LvmamaPriceInfoVo.Product product = new LvmamaPriceInfoVo.Product();

        public void setProduct(LvmamaPriceInfoVo.Product product) {
            this.product = product;
        }

        @JacksonXmlProperty(localName="product")
        public LvmamaPriceInfoVo.Product getProduct (){
            return this.product;
        }

    }
}
