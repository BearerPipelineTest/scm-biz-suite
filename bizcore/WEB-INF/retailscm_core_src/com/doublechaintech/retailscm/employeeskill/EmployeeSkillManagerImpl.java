
package com.doublechaintech.retailscm.employeeskill;















import com.doublechaintech.retailscm.*;import com.doublechaintech.retailscm.BaseViewPage;import com.doublechaintech.retailscm.RetailscmUserContextImpl;import com.doublechaintech.retailscm.employee.CandidateEmployee;import com.doublechaintech.retailscm.employee.Employee;import com.doublechaintech.retailscm.iamservice.*;import com.doublechaintech.retailscm.secuser.SecUser;import com.doublechaintech.retailscm.services.IamService;import com.doublechaintech.retailscm.skilltype.CandidateSkillType;import com.doublechaintech.retailscm.skilltype.SkillType;import com.doublechaintech.retailscm.tree.*;import com.doublechaintech.retailscm.treenode.*;import com.doublechaintech.retailscm.userapp.UserApp;import com.doublechaintech.retailscm.utils.ModelAssurance;
import com.terapico.caf.BlobObject;import com.terapico.caf.DateTime;import com.terapico.caf.Images;import com.terapico.caf.Password;import com.terapico.caf.baseelement.PlainText;import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.uccaf.BaseUserContext;
import com.terapico.utils.*;
import java.math.BigDecimal;
import java.util.*;
import com.doublechaintech.retailscm.search.Searcher;


public class EmployeeSkillManagerImpl extends CustomRetailscmCheckerManager implements EmployeeSkillManager, BusinessHandler{

	// Only some of ods have such function
	




  


	private static final String SERVICE_TYPE = "EmployeeSkill";
	@Override
	public EmployeeSkillDAO daoOf(RetailscmUserContext userContext) {
		return employeeSkillDaoOf(userContext);
	}

	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}



	protected void throwExceptionWithMessage(String value) throws EmployeeSkillManagerException{

		Message message = new Message();
		message.setBody(value);
		throw new EmployeeSkillManagerException(message);

	}



 	protected EmployeeSkill saveEmployeeSkill(RetailscmUserContext userContext, EmployeeSkill employeeSkill, String [] tokensExpr) throws Exception{
 		//return getEmployeeSkillDAO().save(employeeSkill, tokens);

 		Map<String,Object>tokens = parseTokens(tokensExpr);

 		return saveEmployeeSkill(userContext, employeeSkill, tokens);
 	}

 	protected EmployeeSkill saveEmployeeSkillDetail(RetailscmUserContext userContext, EmployeeSkill employeeSkill) throws Exception{


 		return saveEmployeeSkill(userContext, employeeSkill, allTokens());
 	}

 	public EmployeeSkill loadEmployeeSkill(RetailscmUserContext userContext, String employeeSkillId, String [] tokensExpr) throws Exception{

 		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);

		checkerOf(userContext).throwExceptionIfHasErrors( EmployeeSkillManagerException.class);



 		Map<String,Object>tokens = parseTokens(tokensExpr);

 		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,employeeSkill, tokens);
 	}


 	 public EmployeeSkill searchEmployeeSkill(RetailscmUserContext userContext, String employeeSkillId, String textToSearch,String [] tokensExpr) throws Exception{

 		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);

		checkerOf(userContext).throwExceptionIfHasErrors( EmployeeSkillManagerException.class);



 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText(tokens().startsWith(), textToSearch).initWithArray(tokensExpr);

 		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,employeeSkill, tokens);
 	}



 	protected EmployeeSkill present(RetailscmUserContext userContext, EmployeeSkill employeeSkill, Map<String, Object> tokens) throws Exception {


		addActions(userContext,employeeSkill,tokens);
    

		EmployeeSkill  employeeSkillToPresent = employeeSkillDaoOf(userContext).present(employeeSkill, tokens);

		List<BaseEntity> entityListToNaming = employeeSkillToPresent.collectRefercencesFromLists();
		employeeSkillDaoOf(userContext).alias(entityListToNaming);


		renderActionForList(userContext,employeeSkill,tokens);

		return  employeeSkillToPresent;


	}



 	public EmployeeSkill loadEmployeeSkillDetail(RetailscmUserContext userContext, String employeeSkillId) throws Exception{
 		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, allTokens());
 		return present(userContext,employeeSkill, allTokens());

 	}

	public Object prepareContextForUserApp(BaseUserContext userContext,Object targetUserApp) throws Exception{
		
        UserApp userApp=(UserApp) targetUserApp;
        return this.view ((RetailscmUserContext)userContext,userApp.getAppId());
        
    }

	


 	public Object view(RetailscmUserContext userContext, String employeeSkillId) throws Exception{
 		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, viewTokens());
 		markVisited(userContext, employeeSkill);
 		return present(userContext,employeeSkill, viewTokens());

	 }
	 public Object summaryView(RetailscmUserContext userContext, String employeeSkillId) throws Exception{
		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, viewTokens());
		employeeSkill.summarySuffix();
		markVisited(userContext, employeeSkill);
 		return present(userContext,employeeSkill, summaryTokens());

	}
	 public Object analyze(RetailscmUserContext userContext, String employeeSkillId) throws Exception{
		EmployeeSkill employeeSkill = loadEmployeeSkill( userContext, employeeSkillId, analyzeTokens());
		markVisited(userContext, employeeSkill);
		return present(userContext,employeeSkill, analyzeTokens());

	}
 	protected EmployeeSkill saveEmployeeSkill(RetailscmUserContext userContext, EmployeeSkill employeeSkill, Map<String,Object>tokens) throws Exception{
 	
 		return employeeSkillDaoOf(userContext).save(employeeSkill, tokens);
 	}
 	protected EmployeeSkill loadEmployeeSkill(RetailscmUserContext userContext, String employeeSkillId, Map<String,Object>tokens) throws Exception{
		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);

		checkerOf(userContext).throwExceptionIfHasErrors( EmployeeSkillManagerException.class);



 		return employeeSkillDaoOf(userContext).load(employeeSkillId, tokens);
 	}

	







 	protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, EmployeeSkill employeeSkill, Map<String, Object> tokens){
		super.addActions(userContext, employeeSkill, tokens);

		addAction(userContext, employeeSkill, tokens,"@create","createEmployeeSkill","createEmployeeSkill/","main","primary");
		addAction(userContext, employeeSkill, tokens,"@update","updateEmployeeSkill","updateEmployeeSkill/"+employeeSkill.getId()+"/","main","primary");
		addAction(userContext, employeeSkill, tokens,"@copy","cloneEmployeeSkill","cloneEmployeeSkill/"+employeeSkill.getId()+"/","main","primary");

		addAction(userContext, employeeSkill, tokens,"employee_skill.transfer_to_employee","transferToAnotherEmployee","transferToAnotherEmployee/"+employeeSkill.getId()+"/","main","primary");
		addAction(userContext, employeeSkill, tokens,"employee_skill.transfer_to_skill_type","transferToAnotherSkillType","transferToAnotherSkillType/"+employeeSkill.getId()+"/","main","primary");






	}// end method of protected<T extends BaseEntity> void addActions(RetailscmUserContext userContext, EmployeeSkill employeeSkill, Map<String, Object> tokens){








  @Override
  public List<EmployeeSkill> searchEmployeeSkillList(RetailscmUserContext ctx, EmployeeSkillRequest pRequest){
      pRequest.setUserContext(ctx);
      List<EmployeeSkill> list = daoOf(ctx).search(pRequest);
      Searcher.enhance(list, pRequest);
      return list;
  }

  @Override
  public EmployeeSkill searchEmployeeSkill(RetailscmUserContext ctx, EmployeeSkillRequest pRequest){
    pRequest.limit(0, 1);
    List<EmployeeSkill> list = searchEmployeeSkillList(ctx, pRequest);
    if (list == null || list.isEmpty()){
      return null;
    }
    return list.get(0);
  }

	public EmployeeSkill createEmployeeSkill(RetailscmUserContext userContext, String employeeId,String skillTypeId,String description) throws Exception
	{





		checkerOf(userContext).checkDescriptionOfEmployeeSkill(description);


		checkerOf(userContext).throwExceptionIfHasErrors(EmployeeSkillManagerException.class);



		EmployeeSkill employeeSkill=createNewEmployeeSkill();	

			
		Employee employee = loadEmployee(userContext, employeeId,emptyOptions());
		employeeSkill.setEmployee(employee);
		
		
			
		SkillType skillType = loadSkillType(userContext, skillTypeId,emptyOptions());
		employeeSkill.setSkillType(skillType);
		
		
		employeeSkill.setDescription(description);

		employeeSkill = saveEmployeeSkill(userContext, employeeSkill, emptyOptions());
		
		onNewInstanceCreated(userContext, employeeSkill);
		return employeeSkill;


	}
	protected EmployeeSkill createNewEmployeeSkill()
	{

		return new EmployeeSkill();
	}

	protected void checkParamsForUpdatingEmployeeSkill(RetailscmUserContext userContext,String employeeSkillId, int employeeSkillVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		



		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);
		checkerOf(userContext).checkVersionOfEmployeeSkill( employeeSkillVersion);



		

		
		if(EmployeeSkill.DESCRIPTION_PROPERTY.equals(property)){
		
			checkerOf(userContext).checkDescriptionOfEmployeeSkill(parseString(newValueExpr));
		

		}


		checkerOf(userContext).throwExceptionIfHasErrors(EmployeeSkillManagerException.class);



	}



	public EmployeeSkill clone(RetailscmUserContext userContext, String fromEmployeeSkillId) throws Exception{

		return employeeSkillDaoOf(userContext).clone(fromEmployeeSkillId, this.allTokens());
	}

	public EmployeeSkill internalSaveEmployeeSkill(RetailscmUserContext userContext, EmployeeSkill employeeSkill) throws Exception
	{
		return internalSaveEmployeeSkill(userContext, employeeSkill, allTokens());

	}
	public EmployeeSkill internalSaveEmployeeSkill(RetailscmUserContext userContext, EmployeeSkill employeeSkill, Map<String,Object> options) throws Exception
	{
		//checkParamsForUpdatingEmployeeSkill(userContext, employeeSkillId, employeeSkillVersion, property, newValueExpr, tokensExpr);


		synchronized(employeeSkill){
			//will be good when the employeeSkill loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EmployeeSkill.
			if (employeeSkill.isChanged()){
			
			}

      //checkerOf(userContext).checkAndFixEmployeeSkill(employeeSkill);
			employeeSkill = saveEmployeeSkill(userContext, employeeSkill, options);
			return employeeSkill;

		}

	}

	public EmployeeSkill updateEmployeeSkill(RetailscmUserContext userContext,String employeeSkillId, int employeeSkillVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEmployeeSkill(userContext, employeeSkillId, employeeSkillVersion, property, newValueExpr, tokensExpr);



		EmployeeSkill employeeSkill = loadEmployeeSkill(userContext, employeeSkillId, allTokens());
		if(employeeSkill.getVersion() != employeeSkillVersion){
			String message = "The target version("+employeeSkill.getVersion()+") is not equals to version("+employeeSkillVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(employeeSkill){
			//will be good when the employeeSkill loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EmployeeSkill.
			
			employeeSkill.changeProperty(property, newValueExpr);
			employeeSkill = saveEmployeeSkill(userContext, employeeSkill, tokens().done());
			return present(userContext,employeeSkill, mergedAllTokens(tokensExpr));
			//return saveEmployeeSkill(userContext, employeeSkill, tokens().done());
		}

	}

	public EmployeeSkill updateEmployeeSkillProperty(RetailscmUserContext userContext,String employeeSkillId, int employeeSkillVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception
	{
		checkParamsForUpdatingEmployeeSkill(userContext, employeeSkillId, employeeSkillVersion, property, newValueExpr, tokensExpr);

		EmployeeSkill employeeSkill = loadEmployeeSkill(userContext, employeeSkillId, allTokens());
		if(employeeSkill.getVersion() != employeeSkillVersion){
			String message = "The target version("+employeeSkill.getVersion()+") is not equals to version("+employeeSkillVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(employeeSkill){
			//will be good when the employeeSkill loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to EmployeeSkill.

			employeeSkill.changeProperty(property, newValueExpr);
			
			employeeSkill = saveEmployeeSkill(userContext, employeeSkill, tokens().done());
			return present(userContext,employeeSkill, mergedAllTokens(tokensExpr));
			//return saveEmployeeSkill(userContext, employeeSkill, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}

	protected EmployeeSkillTokens tokens(){
		return EmployeeSkillTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return EmployeeSkillTokens.all();
	}
	protected Map<String,Object> analyzeTokens(){
		return tokens().allTokens().analyzeAllLists().done();
	}
	protected Map<String,Object> summaryTokens(){
		return tokens().allTokens().done();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return EmployeeSkillTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherEmployee(RetailscmUserContext userContext, String employeeSkillId, String anotherEmployeeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);
 		checkerOf(userContext).checkIdOfEmployee(anotherEmployeeId);//check for optional reference

 		checkerOf(userContext).throwExceptionIfHasErrors(EmployeeSkillManagerException.class);

 	}
 	public EmployeeSkill transferToAnotherEmployee(RetailscmUserContext userContext, String employeeSkillId, String anotherEmployeeId) throws Exception
 	{
 		checkParamsForTransferingAnotherEmployee(userContext, employeeSkillId,anotherEmployeeId);
 
		EmployeeSkill employeeSkill = loadEmployeeSkill(userContext, employeeSkillId, allTokens());
		synchronized(employeeSkill){
			//will be good when the employeeSkill loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Employee employee = loadEmployee(userContext, anotherEmployeeId, emptyOptions());
			employeeSkill.updateEmployee(employee);
			
			employeeSkill = saveEmployeeSkill(userContext, employeeSkill, emptyOptions());

			return present(userContext,employeeSkill, allTokens());

		}

 	}

	


	public CandidateEmployee requestCandidateEmployee(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateEmployee result = new CandidateEmployee();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("company");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Employee> candidateList = employeeDaoOf(userContext).requestCandidateEmployeeForEmployeeSkill(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 	protected void checkParamsForTransferingAnotherSkillType(RetailscmUserContext userContext, String employeeSkillId, String anotherSkillTypeId) throws Exception
 	{

 		checkerOf(userContext).checkIdOfEmployeeSkill(employeeSkillId);
 		checkerOf(userContext).checkIdOfSkillType(anotherSkillTypeId);//check for optional reference

 		checkerOf(userContext).throwExceptionIfHasErrors(EmployeeSkillManagerException.class);

 	}
 	public EmployeeSkill transferToAnotherSkillType(RetailscmUserContext userContext, String employeeSkillId, String anotherSkillTypeId) throws Exception
 	{
 		checkParamsForTransferingAnotherSkillType(userContext, employeeSkillId,anotherSkillTypeId);
 
		EmployeeSkill employeeSkill = loadEmployeeSkill(userContext, employeeSkillId, allTokens());
		synchronized(employeeSkill){
			//will be good when the employeeSkill loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			SkillType skillType = loadSkillType(userContext, anotherSkillTypeId, emptyOptions());
			employeeSkill.updateSkillType(skillType);
			
			employeeSkill = saveEmployeeSkill(userContext, employeeSkill, emptyOptions());

			return present(userContext,employeeSkill, allTokens());

		}

 	}

	


	public CandidateSkillType requestCandidateSkillType(RetailscmUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateSkillType result = new CandidateSkillType();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("code");

		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<SkillType> candidateList = skillTypeDaoOf(userContext).requestCandidateSkillTypeForEmployeeSkill(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}

 //--------------------------------------------------------------
	

 	protected Employee loadEmployee(RetailscmUserContext userContext, String newEmployeeId, Map<String,Object> options) throws Exception
 	{
    
 		return employeeDaoOf(userContext).load(newEmployeeId, options);
 	  
 	}
 	


	

 	protected SkillType loadSkillType(RetailscmUserContext userContext, String newSkillTypeId, Map<String,Object> options) throws Exception
 	{
    
 		return skillTypeDaoOf(userContext).load(newSkillTypeId, options);
 	  
 	}
 	


	
	//--------------------------------------------------------------

	public void delete(RetailscmUserContext userContext, String employeeSkillId, int employeeSkillVersion) throws Exception {
		//deleteInternal(userContext, employeeSkillId, employeeSkillVersion);
	}
	protected void deleteInternal(RetailscmUserContext userContext,
			String employeeSkillId, int employeeSkillVersion) throws Exception{

		employeeSkillDaoOf(userContext).delete(employeeSkillId, employeeSkillVersion);
	}

	public EmployeeSkill forgetByAll(RetailscmUserContext userContext, String employeeSkillId, int employeeSkillVersion) throws Exception {
		return forgetByAllInternal(userContext, employeeSkillId, employeeSkillVersion);
	}
	protected EmployeeSkill forgetByAllInternal(RetailscmUserContext userContext,
			String employeeSkillId, int employeeSkillVersion) throws Exception{

		return employeeSkillDaoOf(userContext).disconnectFromAll(employeeSkillId, employeeSkillVersion);
	}




	public int deleteAll(RetailscmUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new EmployeeSkillManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}


	protected int deleteAllInternal(RetailscmUserContext userContext) throws Exception{
		return employeeSkillDaoOf(userContext).deleteAll();
	}





	public void onNewInstanceCreated(RetailscmUserContext userContext, EmployeeSkill newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);

    
	}

  
  

  public void sendAllItems(RetailscmUserContext ctx) throws Exception{
    employeeSkillDaoOf(ctx).loadAllAsStream().forEach(
          event -> sendInitEvent(ctx, event)
    );
  }



	// -----------------------------------//  登录部分处理 \\-----------------------------------
	@Override
  protected BusinessHandler getLoginProcessBizHandler(RetailscmUserContextImpl userContext) {
    return this;
  }

	public void onAuthenticationFailed(RetailscmUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// by default, failed is failed, nothing can do
	}
	// when user authenticated success, but no sec_user related, this maybe a new user login from 3-rd party service.
	public void onAuthenticateNewUserLogged(RetailscmUserContext userContext, LoginContext loginContext,
			LoginResult loginResult, IdentificationHandler idHandler, BusinessHandler bizHandler)
			throws Exception {
		// Generally speaking, when authenticated user logined, we will create a new account for him/her.
		// you need do it like :
		// First, you should create new data such as:
		//   EmployeeSkill newEmployeeSkill = this.createEmployeeSkill(userContext, ...
		// Next, create a sec-user in your business way:
		//   SecUser secUser = secUserManagerOf(userContext).createSecUser(userContext, login, mobile ...
		// And set it into loginContext:
		//   loginContext.getLoginTarget().setSecUser(secUser);
		// Next, create an user-app to connect secUser and newEmployeeSkill
		//   UserApp uerApp = userAppManagerOf(userContext).createUserApp(userContext, secUser.getId(), ...
		// Also, set it into loginContext:
		//   loginContext.getLoginTarget().setUserApp(userApp);
		// and in most case, this should be considered as "login success"
		//   loginResult.setSuccess(true);
		//
		// Since many of detailed info were depending business requirement, So,
		throw new Exception("请重载函数onAuthenticateNewUserLogged()以处理新用户登录");
	}
	protected SmartList<UserApp> getRelatedUserAppList(RetailscmUserContext userContext, SecUser secUser) {
    MultipleAccessKey key = new MultipleAccessKey();
    key.put(UserApp.SEC_USER_PROPERTY, secUser.getId());
    key.put(UserApp.APP_TYPE_PROPERTY, EmployeeSkill.INTERNAL_TYPE);
    SmartList<UserApp> userApps = userContext.getDAOGroup().getUserAppDAO().findUserAppWithKey(key, EO);
    return userApps;
  }
	// -----------------------------------\\  登录部分处理 //-----------------------------------



	// -----------------------------------// list-of-view 处理 \\-----------------------------------
    protected void enhanceForListOfView(RetailscmUserContext userContext,SmartList<EmployeeSkill> list) throws Exception {
    	if (list == null || list.isEmpty()){
    		return;
    	}
		List<Employee> employeeList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, Employee.class);
		userContext.getDAOGroup().enhanceList(employeeList, Employee.class);
		List<SkillType> skillTypeList = RetailscmBaseUtils.collectReferencedObjectWithType(userContext, list, SkillType.class);
		userContext.getDAOGroup().enhanceList(skillTypeList, SkillType.class);


    }
	
	public Object listByEmployee(RetailscmUserContext userContext,String employeeId) throws Exception {
		return listPageByEmployee(userContext, employeeId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageByEmployee(RetailscmUserContext userContext,String employeeId, int start, int count) throws Exception {
		SmartList<EmployeeSkill> list = employeeSkillDaoOf(userContext).findEmployeeSkillByEmployee(employeeId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(EmployeeSkill.class);
		page.setContainerObject(Employee.withId(employeeId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("员工技能列表");
		page.setRequestName("listByEmployee");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listByEmployee/%s/",  getBeanName(), employeeId)));

		page.assemblerContent(userContext, "listByEmployee");
		return page.doRender(userContext);
	}
  
	public Object listBySkillType(RetailscmUserContext userContext,String skillTypeId) throws Exception {
		return listPageBySkillType(userContext, skillTypeId, 0, 20);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object listPageBySkillType(RetailscmUserContext userContext,String skillTypeId, int start, int count) throws Exception {
		SmartList<EmployeeSkill> list = employeeSkillDaoOf(userContext).findEmployeeSkillBySkillType(skillTypeId, start, count, new HashMap<>());
		enhanceForListOfView(userContext, list);
		RetailscmCommonListOfViewPage page = new RetailscmCommonListOfViewPage();
		page.setClassOfList(EmployeeSkill.class);
		page.setContainerObject(SkillType.withId(skillTypeId));
		page.setRequestBeanName(this.getBeanName());
		page.setDataList((SmartList)list);
		page.setPageTitle("员工技能列表");
		page.setRequestName("listBySkillType");
		page.setRequestOffset(start);
		page.setRequestLimit(count);
		page.setDisplayMode("auto");
		page.setLinkToUrl(TextUtil.encodeUrl(String.format("%s/listBySkillType/%s/",  getBeanName(), skillTypeId)));

		page.assemblerContent(userContext, "listBySkillType");
		return page.doRender(userContext);
	}
  
  // -----------------------------------\\ list-of-view 处理 //-----------------------------------v
  
 	/**
	 * miniprogram调用返回固定的detail class
	 *
	 * @return
	 * @throws Exception
	 */
 	public Object wxappview(RetailscmUserContext userContext, String employeeSkillId) throws Exception{
    SerializeScope vscope = SerializeScope.EXCLUDE().nothing();
		EmployeeSkill merchantObj = (EmployeeSkill) this.view(userContext, employeeSkillId);
    String merchantObjId = employeeSkillId;
    String linkToUrl =	"employeeSkillManager/wxappview/" + merchantObjId + "/";
    String pageTitle = "员工技能"+"详情";
		Map result = new HashMap();
		List propList = new ArrayList();
		List sections = new ArrayList();
 
		propList.add(
				MapUtil.put("id", "1-id")
				    .put("fieldName", "id")
				    .put("label", "ID")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("id", merchantObj.getId());

		propList.add(
				MapUtil.put("id", "2-employee")
				    .put("fieldName", "employee")
				    .put("label", "员工")
				    .put("type", "auto")
				    .put("linkToUrl", "employeeManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"family_name\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"title\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("employee", merchantObj.getEmployee());

		propList.add(
				MapUtil.put("id", "3-skillType")
				    .put("fieldName", "skillType")
				    .put("label", "技能类型")
				    .put("type", "auto")
				    .put("linkToUrl", "skillTypeManager/wxappview/:id/")
				    .put("displayMode", "{\"brief\":\"description\",\"imageUrl\":\"\",\"name\":\"auto\",\"title\":\"code\",\"imageList\":\"\"}")
				    .into_map()
		);
		result.put("skillType", merchantObj.getSkillType());

		propList.add(
				MapUtil.put("id", "4-description")
				    .put("fieldName", "description")
				    .put("label", "描述")
				    .put("type", "text")
				    .put("linkToUrl", "")
				    .put("displayMode", "{}")
				    .into_map()
		);
		result.put("description", merchantObj.getDescription());

		//处理 sectionList

		result.put("propList", propList);
		result.put("sectionList", sections);
		result.put("pageTitle", pageTitle);
		result.put("linkToUrl", linkToUrl);

		vscope.field("propList", SerializeScope.EXCLUDE())
				.field("sectionList", SerializeScope.EXCLUDE())
				.field("pageTitle", SerializeScope.EXCLUDE())
				.field("linkToUrl", SerializeScope.EXCLUDE());
		userContext.forceResponseXClassHeader("com.terapico.appview.DetailPage");
		return BaseViewPage.serialize(result, vscope);
	}

  










}




