package jp.co.info.ais.ams.controller;

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

import jp.co.info.ais.ams.common.ExValidation;
import jp.co.info.ais.ams.common.Page;
import jp.co.info.ais.ams.domain.Accessories;
import jp.co.info.ais.ams.domain.Asset;
import jp.co.info.ais.ams.domain.MaintenanceHistory;
import jp.co.info.ais.ams.service.AssetService;

@Controller
@RequestMapping("/asset")
public class AssetController {
	private static final Logger logger = LogManager.getLogger(AssetController.class);
	@Autowired
	ExValidation exValidation;
	@Autowired
	private AssetService assetService;

	@Autowired
	HttpSession session;


	/**
	 * 資産管理の初期画面
	 * コード値セッティング
	 *
	 * @param Model
	 * @return String 画面名
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String assetList(Model model) {
    	try {
    		// コード値セッティング
	    	model.addAttribute("productCode", assetService.selectProductCode());
	    	model.addAttribute("stateCode", assetService.selectStateCode());
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return "asset";
    }
    /**
     * 資産変更ページ
     * @param assetSeq
     * @param model
     * @return
     */
    @RequestMapping(value = "/up"+"{assetSeq}", method = RequestMethod.GET)
    public String loadUpdateGET(@PathVariable("assetSeq") int assetSeq, Model model) {
    	try {
        	// 資産情報照会
    		model.addAttribute("asset", assetService.selectAsset(assetSeq));
        	// コード値セッティング
    		model.addAttribute("productCode", assetService.selectProductCode());
    		model.addAttribute("stateCode", assetService.selectStateCode());
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return "assetUpdate";
    }
    /**
     * 資産変更
     * @param model
     * @param assetSeq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSequence", method = RequestMethod.POST)
    public Asset loadUpdatePOST(Model model,@RequestBody int assetSeq) {
    	try {
    		// 資産情報照会
    		return assetService.selectAsset(assetSeq);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return null;
		}
    }

	/**
	 * 検索条件に応じた資産リスト出力
	 *
	 * @param Page<Asset> dataTableObject
	 * @return Page<Asset> dataTableObject
	 */
    @RequestMapping("/getAssetlist")
    @ResponseBody
    public Page<Asset> getAssetlist(@RequestBody Page<Asset> page) {
    	try {
	    	// 検索条件
	    	Asset condition = new Asset();
	    	// ページング処理
	    	condition.setLength(page.getLength());
	    	condition.setStart(page.getStart());

	    	// 資産管理番号セッティング
	    	String assetNumber = page.getColumns().get(0).getSearch().getValue();
	    	if(null != assetNumber && !assetNumber.equals("")){
	    		condition.setAssetNumber(assetNumber);
	    	}

	    	// 区分コードセッティング
	    	String kubunCode = page.getColumns().get(1).getSearch().getValue();
	    	condition.setKubunCode(kubunCode);

	    	// 状態コードセッティング
	    	String statusCode = page.getColumns().get(2).getSearch().getValue();
	    	condition.setStatusCode(statusCode);

	    	// メーカ名セッティング
	    	String makerName = page.getColumns().get(3).getSearch().getValue();
	    	if(null != makerName && !makerName.equals("")){
	    		condition.setMakerName(makerName);
	    	}

	    	// モデル名セッティング
	    	String modelName = page.getColumns().get(4).getSearch().getValue();
	    	if(null != modelName && !modelName.equals("")){
	    		condition.setModelName(modelName);
	    	}

	    	// 購入日セッティング
	    	String date = page.getColumns().get(5).getSearch().getValue();
	    	if(null != date && !date.equals("")){
	    		condition.setPurchaseDate(date);
	    	}

	    	// リスト照会
	        List<Asset> list = assetService.selectList(condition);

	        // データをページオブジェクトにセット
	        page.setData(list);

	        // 総数
	        int totalCount = assetService.selectCount(condition);

	        // 総数をページオブジェクトにセット
	        page.setRecordsFiltered(totalCount);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return page;
    }

	/**
	 * 資産詳細情報照会
	 *
	 * @param int 資産シーケンス
	 * @return Asset  資産Object
	 */
    @RequestMapping(value = "/assetInfoAjax")
    @ResponseBody
    public Asset assetInfoAjax(@RequestBody int assetSeq) {
    	try {
    		// 資産情報照会
    		return assetService.selectAsset(assetSeq);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return null;
		}
    }

	/**
	 * 資産情報登録
	 *
	 * @param Model
	 * @param Asset 資産情報
	 * @return Asset 結果値
	 */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Asset assetInsert(Asset asset) {
    	try {
	    	//セッションにあるID値セッティング
	    	String id = (String) session.getAttribute("id");
	    	asset.setInsertId(id); asset.setUpdateId(id);

	    	// 資産情報登録
	    	assetService.insertAsset(asset);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
    	return asset;
    }

	/**
	 * 資産情報削除
	 *
	 * @param int 資産シーケンス
	 * @return int 結果値
	 */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public int assetDelete(@RequestBody int assetSeq) {
    	int result = 0;
    	try {
	    	// 資産情報削除
	    	result = assetService.deleteAsset(assetSeq);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }

	/**
	 * 資産情報修正の初期画面
	 * コード値セッティング
	 * 修正割資産情報セッティング
	 *
	 * @param int 資産シーケンス
	 * @param Model
	 * @return int 結果値
	 */
    @RequestMapping(value = "/{assetSeq}", method = RequestMethod.GET)
    public String assetUpdateInfo(@PathVariable("assetSeq") int assetSeq, Model model) {

    	try {
        	// 資産情報照会
    		model.addAttribute("asset", assetService.selectAsset(assetSeq));
        	// コード値セッティング
    		model.addAttribute("productCode", assetService.selectProductCode());
    		model.addAttribute("stateCode", assetService.selectStateCode());
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return "assetUpdate" ;
    }

	/**
	 * 資産情報修正
	 *
	 * @param Asset 資産情報
	 * @return int 結果値
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int assetUpdate(Asset asset) {
    	int result = 0;
    	try {
        	//セッションにあるID値セッティング
        	String id = (String) session.getAttribute("id");
        	asset.setInsertId(id); asset.setUpdateId(id);
        	// 資産情報更新
        	result = assetService.updateAsset(asset);
        	// 更新データをページにセット
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }

	/**
	 * 付属品情報登録
	 *
	 * @param Accessories 付属品情報
	 * @return Accessories 結果値
	 */
    @RequestMapping(value = "/addAccessories", method = RequestMethod.POST)
    @ResponseBody
    public Accessories addAccessories(@RequestBody Accessories accessories) {
    	try {
	    	//セッションにあるID値セッティング
	    	String id = (String) session.getAttribute("id");
	    	accessories.setInsertId(id); accessories.setUpdateId(id);
	    	// 付属品登録
	    	assetService.insertAccessories(accessories);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return accessories;
    }

	/**
	 * 付属品情報修正
	 *
	 * @param Accessories 付属品情報
	 * @return int 結果値
	 */
    @RequestMapping(value = "/updateAccessories", method = RequestMethod.POST)
    @ResponseBody
    public int updateAccessories(@RequestBody Accessories accessories) {
    	int result = 0;
    	try {
	    	//セッションにあるID値セッティング
	    	String id = (String) session.getAttribute("id");
	    	accessories.setInsertId(id); accessories.setUpdateId(id);
	    	// 付属品更新
	    	result = assetService.updateAccessories(accessories);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }

	/**
	 * 付属品情報削除
	 *
	 * @param Accessories 付属品情報
	 * @return int 結果値
	 */
    @RequestMapping(value = "/deleteAccessories", method = RequestMethod.POST)
    @ResponseBody
    public int deleteAccessories(@RequestBody Accessories accessories) {
    	logger.debug(":::::::::::::::::{}", accessories);
    	int result = 0;
    	try {
	    	// 付属品削除
	    	result = assetService.deleteAccessories(accessories);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }

	/**
	 * 保守履歴情報登録
	 *
	 * @param MaintenanceHistory 保守履歴情報
	 * @return MaintenanceHistory 結果値
	 */
    @RequestMapping(value = "/addMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public MaintenanceHistory addMaintenanceHistory(@RequestBody MaintenanceHistory maintenanceHistory) {
    	try {
	    	//セッションにあるID値セッティング
	    	String id = (String) session.getAttribute("id");
	    	maintenanceHistory.setInsertId(id); maintenanceHistory.setUpdateId(id);
	    	// 保守履歴登録
	    	assetService.insertMaintenanceHistory(maintenanceHistory);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return maintenanceHistory;
    }

	/**
	 * 保守履歴情報修正
	 *
	 * @param Accessories 保守履歴情報
	 * @return int 結果値
	 */
    @RequestMapping(value = "/updateMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public int updateMaintenanceHistory(Model model, @RequestBody MaintenanceHistory maintenanceHistory) {
    	int result = 0;
    	try {
	    	//セッションにあるID値セッティング
	    	String id = (String) session.getAttribute("id");
	    	maintenanceHistory.setInsertId(id); maintenanceHistory.setUpdateId(id);
	    	// 保守履歴更新
	    	result = assetService.updateMaintenanceHistory(maintenanceHistory);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }

	/**
	 * 保守履歴情報削除
	 *
	 * @param Accessories 保守履歴情報
	 * @return int 結果値
	 */
    @RequestMapping(value = "/deleteMaintenanceHistory", method = RequestMethod.POST)
    @ResponseBody
    public int deleteMaintenanceHistory(Model model, @RequestBody MaintenanceHistory maintenanceHistory) {
    	int result = 0;
    	try {
	    	// 保守履歴削除
	    	result = assetService.deleteMaintenanceHistory(maintenanceHistory);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
		}
        return result;
    }
}
