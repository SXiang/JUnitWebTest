package common.source;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import surveyor.api.entities.InvestigationReports;

public interface PCubedApiInterface {
	@GET("/Account/login")
	Call<ResponseBody> getRequestToken();

	@FormUrlEncoded
	@POST("/Account/login")
	Call<ResponseBody> login(@Field("Username") String username, @Field("Password") String password, @Field("__RequestVerificationToken") String requestVerificationToken);

	@GET("/Investigation/GetReportsByType?sEcho=3&sSearch")
	Call<InvestigationReports> getInvestigationReports(@Query("reportType") String reportType, @Query("iDisplayStart") Integer startIdx, @Query("iDisplayLength") Integer size);

	@GET("/Reports/ComplianceReports")
	Call<ResponseBody> getComplianceReportsPage();
}
