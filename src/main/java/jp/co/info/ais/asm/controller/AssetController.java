package jp.co.info.ais.asm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.asm.common.Page;
import jp.co.info.ais.asm.domain.Accessories;
import jp.co.info.ais.asm.domain.Asset;
import jp.co.info.ais.asm.domain.MaintenanceHistory;
import jp.co.info.ais.asm.service.AssetService;

@Controller
@RequestMapping("/asset")
public class AssetController {
	private static final Logger logger = LogManager.getLogger(AssetController.class);

	@Autowired
	private AssetService assetService;

	@Autowired
	HttpSession session;



    @RequestMapping(value = "", method = RequestMethod.GET)
    public String assetList(Model model) {
    	// コード値セッティング
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
        return "asset";
    }

    @RequestMapping("/getAssetlist")
    @ResponseBody
    public Page<Asset> getAssetlist(@RequestBody Page<Asset> page) {
    	// 検索条件
    	Asset condition = new Asset();
    	// ページング処理
    	condition.setLength(page.getLength());
    	condition.setStart(page.getStart());

    	// 資産管理番号
    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
    	if(null != assetNumber && !assetNumber.equals("")){
    		logger.debug(page.getColumns().get(0).getSearch().getValue());
    		condition.setAssetNumber(assetNumber);
    	}
    	
    	// 区分コード
    	String kubunCode = page.getColumns().get(1).getSearch().getValue();
    	condition.setKubunCode(kubunCode);

    	// 状態コード
    	String statusCode = page.getColumns().get(2).getSearch().getValue();
    	condition.setStatusCode(statusCode);

    	// メーカ名
    	String makerName = page.getColumns().get(3).getSearch().getValue();
    	if(null != makerName && !makerName.equals("")){
    		logger.debug(page.getColumns().get(3).getSearch().getValue());
    		condition.setMakerName(makerName);
    	}

    	// モデル名
    	String modelName = page.getColumns().get(4).getSearch().getValue();
    	if(null != modelName && !modelName.equals("")){
    		logger.debug(page.getColumns().get(4).getSearch().getValue());
    		condition.setModelName(modelName);
    	}

    	// 購入日検索範囲
    	String date = page.getColumns().get(5).getSearch().getValue();
    	if(null != date && !date.equals("")){
    		// 空白及び'/'除去
    		date = date.replaceAll("[ /]", "");
    		// '-'基準切り
    		String[] dateArr = date.split("-");
			condition.setStartPurchaseDate(dateArr[0]);
			condition.setEndPurchaseDate(dateArr[1]);
    	}

    	// リスト照会
        List<Asset> list = assetService.selectList(condition);

        // データをページオブジェクトにセット
        page.setData(list);

        // 総数
        int totalCount = assetService.selectCount(condition);

        // 総数をページオブジェクトにセット
        page.setRecordsFiltered(totalCount);
        return page;
    }

    @RequestMapping(value = "/assetInfoAjax")
    @ResponseBody
    public Asset assetInfoAjax(@RequestBody int assetSeq) {
    	// 資産情報照会
        return assetService.selectAsset(assetSeq);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String assetInsert(Model model, Asset asset) {
    	//セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	asset.setInsertId(id); asset.setUpdateId(id);
    	
    	// 資産情報登録
    	assetService.insertAsset(asset);
    	
    	// コード値セッティング
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
        return "asset";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int assetDelete(Model model, @RequestBody int assetSeq) {
    	// 資産情報削除
    	int result = assetService.deleteAsset(assetSeq);
        return result;
    }

    @RequestMapping(value = "/{assetSeq}", method = RequestMethod.GET)
    public String assetInfo(@PathVariable("assetSeq") int assetSeq, Model model) {
    	// 資産情報照会
    	model.addAttribute("asset", assetService.selectAsset(assetSeq));
    	// コード値セッティング
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
        return "assetUpdate";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String assetUpdate(Model model, Asset asset) {
    	//　セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	asset.setInsertId(id); asset.setUpdateId(id);
    	logger.debug(asset.toString());
    	// 空白及び'-'除去
    	asset.setPurchaseDate(asset.getPurchaseDate().replaceAll("-", ""));
    	// コード値セッティング
    	model.addAttribute("productCode", assetService.selectProductCode());
    	model.addAttribute("stateCode", assetService.selectStateCode());
    	// 資産情報更新
    	int result = assetService.updateAsset(asset);
    	// 更新データをページにセット
    	model.addAttribute("asset", assetService.selectAsset(asset.getAssetSeq()));
        return "assetUpdate";
    }

    @RequestMapping(value = "/addAccessories", method = RequestMethod.POST)
    @ResponseBody
    public Accessories addAccessories(Model model, @RequestBody Accessories accessories) {
    	//セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	accessories.setInsertId(id); accessories.setUpdateId(id);
    	logger.debug(accessories.toString());
    	// 付属品登録
    	assetService.insertAccessories(accessories);
        return accessories;
    }

    @RequestMapping(value = "/updateAccessories", method = RequestMethod.POST)
    @ResponseBody
    public Accessories updateAccessories(Model model, @RequestBody Accessories accessories) {
    	//セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	accessories.setInsertId(id); accessories.setUpdateId(id);
    	// 付属品更新
    	int result = assetService.updateAccessories(accessories);
        return accessories;
    }

    @RequestMapping(value = "/deleteAccessories", method = RequestMethod.POST)
    @ResponseBody
    public int deleteAccessories(Model model, @RequestBody Accessories accessories) {
    	// 付属品削除
    	int result = assetService.deleteAccessories(accessories);
        return result;
    }

    @RequestMapping(value = "/addMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public MaintenanceHistory addMaintenanceHistory(Model model, @RequestBody MaintenanceHistory maintenanceHistory) {
    	//セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	maintenanceHistory.setInsertId(id); maintenanceHistory.setUpdateId(id);
    	// 保守履歴登録
    	assetService.insertMaintenanceHistory(maintenanceHistory);
        return maintenanceHistory;
    }

    @RequestMapping(value = "/updateMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public MaintenanceHistory updateMaintenanceHistory(Model model, @RequestBody MaintenanceHistory maintenanceHistory) {
    	//セッションにあるID値セッティング
    	String id = (String) session.getAttribute("id");
    	maintenanceHistory.setInsertId(id); maintenanceHistory.setUpdateId(id);
    	// 保守履歴更新
    	int result = assetService.updateMaintenanceHistory(maintenanceHistory);
        return maintenanceHistory;
    }

    @RequestMapping(value = "/deleteMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public int deleteMaintenanceHistory(Model model, @RequestBody MaintenanceHistory maintenanceHistory) {
    	// 保守履歴削除
    	int result = assetService.deleteMaintenanceHistory(maintenanceHistory);
        return result;
    }
}
