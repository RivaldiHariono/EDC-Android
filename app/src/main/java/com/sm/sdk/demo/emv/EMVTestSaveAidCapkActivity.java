package com.sm.sdk.demo.emv;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sm.sdk.demo.BaseAppCompatActivity;
import com.sm.sdk.demo.MyApplication;
import com.sm.sdk.demo.R;
import com.sunmi.pay.hardware.aidlv2.bean.AidV2;
import com.sunmi.pay.hardware.aidlv2.bean.CapkV2;

public class EMVTestSaveAidCapkActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_aid_capk);
        initView();
    }

    private void initView() {
        initToolbarBringBack("测试保存Aid/Capk");
        findViewById(R.id.test).setOnClickListener(v -> {
            testSaveAidCapk();
        });
    }

    private void testSaveAidCapk() {
        String[] aids = {
                "9F01060000000000009F0607A00000000310109F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D0800000000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080101DFC1090101DFC10A0100DFC10B0100",
                "9F01060000000000009F0609A000000003101000109F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D0800000000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080101DFC1090101DFC10A0100DFC10B0100",
                "9F01060000000000009F0609A000000003101000209F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D0800000000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080101DFC1090101DFC10A0100DFC10B0100",
                "9F01060000000000009F0607A00000000320109F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D0800000000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080101DFC1090101DFC10A0100DFC10B0100",
                "9F01060000000000009F0607A00000000410109F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D086C7E0000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501CA000DF1205FC501C8800DF13050000000000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F060bA0000000041010D71000009F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D086C7E0000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F060bA0000000041010D71000109F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D086C7E0000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F060bA0000000041010D71000209F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D086C7E0000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F060bA0000000041010D71000509F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D086C7E0000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F0607A00000000430609F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D084C7E8000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
                "9F01060000000000009F0609A0000000043060D0569F090200009F150200009F1610000000000000000000000000000000009F1B040000C3509F1C0800000000000000009F1D084C7E8000000000009F3C0207109F3D01009F4E818000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009F7B06000000000000DF010100DF1105FC501C8800DF1205FC501C8800DF13050000880000DF1400DF150400000000DF160100DF170100DF1906000000050000DF2006000050000000DF2106000000005000DF81010400000000DF810200DFC1080100DFC1090100DFC10A0100DFC10B0100",
        };
        String[] capks = {
                "9F0605A0000000039F220150DF027fD11197590057B84196C2F4D11A8F3C05408F422A35D702F90106EA5B019BB28AE607AA9CDEBCD0D81A38D48C7EBB0062D287369EC0C42124246AC30D80CD602B7238D51084DED4698162C59D25EAC1E66255B4DB2352526EF0982C3B8AD3D1CCE85B01DB5788E75E09F44BE7361366DEF9D1E1317B05E5D0FF5290F88A0DB4DF031454CD6907060D0DC2BDD4C43CDE170D07D006E321DF0403010001DF0503191207DF060101DF070101",
                "9F0605A0000000039F220151DF02818fDB5FA29D1FDA8C1634B04DCCFF148ABEE63C772035C79851D3512107586E02A917F7C7E885E7C4A7D529710A145334CE67DC412CB1597B77AA2543B98D19CF2B80C522BDBEA0F1B113FA2C86216C8C610A2D58F29CF3355CEB1BD3EF410D1EDD1F7AE0F16897979DE28C6EF293E0A19282BD1D793F1331523FC71A22880046C01A3653D14C6B4851A5C029478E757FDF03140DCA9CE0E4C8BBE982F3110D324550C21727FBEBDF040103DF0503191207DF060101DF070101",
                "9F0605A0000000039F220152DF0281afAFF740F8DBE763F333A1013A43722055C8E22F41779E219B0E1C409D60AFD45C8789C57EECD71EA4A269A675916CC1C5E1A05A35BD745A79F94555CE29612AC338769665B87C3CA8E1AC4957F9F61FA7BFFE4E17631E937837CABF43DD6183D6360A228A3EBC73A1D1CDC72BF09953C81203AB7E492148E4CB774CDDFAAC354D0DD4F8C8A0E9C70B877EA79F2C22E4CE52C69F3EF376F61B0F43A540FE96C63F586310C3B6E39C78C4D647CADB5933DF03144FC210D7089BB05002DA357FAFBF1E6FC64C1A8EDF040103DF0503191207DF060101DF070101",
                "9F0605A0000000039F220189DF0281c0E5E195705CE61A0672B8367E7A51713927A04289EA308328FAD28071ECEAE889B3C4F29AC3BDE46772B00D42FD05F27228820F2693990F81B0F6928E240D957EC4484354CD5E5CA9092B444741A0394D3476651232474A9B87A961DA8DD96D90F036E9B3C52FB09766BDA4D6BC3BDADBC89122B74068F8FA04026C5FA8EF398BC3AB3992A87F6A785CC779BA99F170956623D67A18EB8324263D626BE85BFF77B8B981C0A3F7849C4F3D8E20542955D19128198547B47AE34DF67F28BE433F33DF03147170850B97F83952045CF9CA8B7612DFEB69E9EFDF040103DF0503231231DF060101DF070101",
                "9F0605A0000000039F220192DF0281b0996AF56F569187D09293C14810450ED8EE3357397B18A2458EFAA92DA3B6DF6514EC060195318FD43BE9B8F0CC669E3F844057CBDDF8BDA191BB64473BC8DC9A730DB8F6B4EDE3924186FFD9B8C7735789C23A36BA0B8AF65372EB57EA5D89E7D14E9C7B6B557460F10885DA16AC923F15AF3758F0F03EBD3C5C2C949CBA306DB44E6A2C076C5F67E281D7EF56785DC4D75945E491F01918800A9E2DC66F60080566CE0DAF8D17EAD46AD8E30A247C9FDF0314429C954A3859CEF91295F663C963E582ED6EB253DF040103DF0503191231DF060101DF070101",
                "9F0605A0000000039F220194DF0281f8ACD2B12302EE644F3F835ABD1FC7A6F62CCE48FFEC622AA8EF062BEF6FB8BA8BC68BBF6AB5870EED579BC3973E121303D34841A796D6DCBC41DBF9E52C4609795C0CCF7EE86FA1D5CB041071ED2C51D2202F63F1156C58A92D38BC60BDF424E1776E2BC9648078A03B36FB554375FC53D57C73F5160EA59F3AFC5398EC7B67758D65C9BFF7828B6B82D4BE124A416AB7301914311EA462C19F771F31B3B57336000DFF732D3B83DE07052D730354D297BEC72871DCCF0E193F171ABA27EE464C6A97690943D59BDABB2A27EB71CEEBDAFA1176046478FD62FEC452D5CA393296530AA3F41927ADFE434A2DF2AE3054F8840657A26E0FC617DF0314C4A3C43CCF87327D136B804160E47D43B60E6E0FDF040103DF0503181231DF060101DF070101",
                "9F0605A0000000039F220195DF028190BE9E1FA5E9A803852999C4AB432DB28600DCD9DAB76DFAAA47355A0FE37B1508AC6BF38860D3C6C2E5B12A3CAAF2A7005A7241EBAA7771112C74CF9A0634652FBCA0E5980C54A64761EA101A114E0F0B5572ADD57D010B7C9C887E104CA4EE1272DA66D997B9A90B5A6D624AB6C57E73C8F919000EB5F684898EF8C3DBEFB330C62660BED88EA78E909AFF05F6DA627BDF0314EE1511CEC71020A9B90443B37B1D5F6E703030F6DF040103DF0503181207DF060101DF070101",
                "9F0605A0000000039F220196DF027fB74586D19A207BE6627C5B0AAFBC44A2ECF5A2942D3A26CE19C4FFAEEE920521868922E893E7838225A3947A2614796FB2C0628CE8C11E3825A56D3B1BBAEF73A5C6A81F36F8625395126FA983C5216D3166D48ACDE8A431212FF763A7F79D9EDB7FED76B485DE45BEB829A3D4730848A366D3324C3027032FF8D16A1E44D8DF0314DE62F3F942C92F959948AF3A3B246BE8F78902DADF040103DF0503191207DF060101DF070101",
                "9F0605A0000000039F220198DF0270CA026E52A695E72BD30AF928196EEDC9FAF4A619F2492E3FB31169789C276FFBB7D43116647BA9E0D106A3542E3965292CF77823DD34CA8EEC7DE367E08070895077C7EFAD939924CB187067DBF92CB1E785917BD38BACE0C194CA12DF0CE5B7A50275AC61BE7C3B436887CA98C9FD39DF0314E7AC9AA8EED1B5FF1BD532CF1489A3E5557572C1DF040103DF0503181207DF060101DF070101",
                "9F0605A0000000039F220199DF028180AB79FCC9520896967E776E64444E5DCDD6E13611874F3985722520425295EEA4BD0C2781DE7F31CD3D041F565F747306EED62954B17EDABA3A6C5B85A1DE1BEB9A34141AF38FCF8279C9DEA0D5A6710D08DB4124F041945587E20359BAB47B7575AD94262D4B25F264AF33DEDCF28E09615E937DE32EDC03C54445FE7E382777DF03144ABFFD6B1C51212D05552E431C5B17007D2F5E6DDF040103DF0503181207DF060101DF070101",
                "9F0605A0000000049F2201EFDF0281f8A191CB87473F29349B5D60A88B3EAEE0973AA6F1A082F358D849FDDFF9C091F899EDA9792CAF09EF28F5D22404B88A2293EEBBC1949C43BEA4D60CFD879A1539544E09E0F09F60F065B2BF2A13ECC705F3D468B9D33AE77AD9D3F19CA40F23DCF5EB7C04DC8F69EBA565B1EBCB4686CD274785530FF6F6E9EE43AA43FDB02CE00DAEC15C7B8FD6A9B394BABA419D3F6DC85E16569BE8E76989688EFEA2DF22FF7D35C043338DEAA982A02B866DE5328519EBBCD6F03CDD686673847F84DB651AB86C28CF1462562C577B853564A290C8556D818531268D25CC98A4CC6A0BDFFFDA2DCCA3A94C998559E307FDDF915006D9A987B07DDAEB3BDF031421766EBB0EE122AFB65D7845B73DB46BAB65427ADF040103DF0503161231DF060101DF070101",
                "9F0605A0000000049F2201F1DF0281b0A0DCF4BDE19C3546B4B6F0414D174DDE294AABBB828C5A834D73AAE27C99B0B053A90278007239B6459FF0BBCD7B4B9C6C50AC02CE91368DA1BD21AAEADBC65347337D89B68F5C99A09D05BE02DD1F8C5BA20E2F13FB2A27C41D3F85CAD5CF6668E75851EC66EDBF98851FD4E42C44C1D59F5984703B27D5B9F21B8FA0D93279FBBF69E090642909C9EA27F898959541AA6757F5F624104F6E1D3A9532F2A6E51515AEAD1B43B3D7835088A2FAFA7BE7DF0314D8E68DA167AB5A85D8C3D55ECB9B0517A1A5B4BBDF040103DF0503201231DF060101DF070101",
                "9F0605A0000000049F2201F3DF02819098F0C770F23864C2E766DF02D1E833DFF4FFE92D696E1642F0A88C5694C6479D16DB1537BFE29E4FDC6E6E8AFD1B0EB7EA0124723C333179BF19E93F10658B2F776E829E87DAEDA9C94A8B3382199A350C077977C97AFF08FD11310AC950A72C3CA5002EF513FCCC286E646E3C5387535D509514B3B326E1234F9CB48C36DDD44B416D23654034A66F403BA511C5EFA3DF0314A69AC7603DAF566E972DEDC2CB433E07E8B01A9ADF040103DF0503151231DF060101DF070101",
                "9F0605A0000000049F2201F4DF0281b09E2F74BF4AB521019735BFC7E4CBC56B6F64AFF1ED7B79998EE5B3DFFE23DFC8E2DD0025575AF94DE814264528AF6F8005A538B3D6AE881B350F89595588E51F7423E711109DEC169FDD560602D80EF46E582C8C546C8930394BD534412A88CC9FF4DFC08AE716A595EF1AF7C32EDFCF996433EB3C36BCE093E44E0BDE228E0299A0E358BF28308DB4739815DD09F1E89654CC7CC193E2AC17C4DA335D904B8EC06ACFBDE083F76933C969672E9AFEA3DF0314BF6B5B9C47134E494571732A4903C935874682B9DF040103DF0503200207DF060101DF070101",
                "9F0605A0000000049F2201F5DF0281f8A6E6FB72179506F860CCCA8C27F99CECD94C7D4F3191D303BBEE37481C7AA15F233BA755E9E4376345A9A67E7994BDC1C680BB3522D8C93EB0CCC91AD31AD450DA30D337662D19AC03E2B4EF5F6EC18282D491E19767D7B24542DFDEFF6F62185503532069BBB369E3BB9FB19AC6F1C30B97D249EEE764E0BAC97F25C873D973953E5153A42064BBFABFD06A4BB486860BF6637406C9FC36813A4A75F75C31CCA9F69F8DE59ADECEF6BDE7E07800FCBE035D3176AF8473E23E9AA3DFEE221196D1148302677C720CFE2544A03DB553E7F1B8427BA1CC72B0F29B12DFEF4C081D076D353E71880AADFF386352AF0AB7B28ED49E1E672D11F9DF0314C2239804C8098170BE52D6D5D4159E81CE8466BFDF0403010001DF0503200207DF060101DF070101",
                "9F0605A0000000049F2201F8DF028180A1F5E1C9BD8650BD43AB6EE56B891EF7459C0A24FA84F9127D1A6C79D4930F6DB1852E2510F18B61CD354DB83A356BD190B88AB8DF04284D02A4204A7B6CB7C5551977A9B36379CA3DE1A08E69F301C95CC1C20506959275F41723DD5D2925290579E5A95B0DF6323FC8E9273D6F849198C4996209166D9BFC973C361CC826E1DF0314F06ECC6D2AAEBF259B7E755A38D9A9B24E2FF3DDDF040103DF0503200207DF060101DF070101",
                "9F0605A0000000049F2201FADF028190A90FCD55AA2D5D9963E35ED0F440177699832F49C6BAB15CDAE5794BE93F934D4462D5D12762E48C38BA83D8445DEAA74195A301A102B2F114EADA0D180EE5E7A5C73E0C4E11F67A43DDAB5D55683B1474CC0627F44B8D3088A492FFAADAD4F42422D0E7013536C3C49AD3D0FAE96459B0F6B1B6056538A3D6D44640F94467B108867DEC40FAAECD740C00E2B7A8852DDF03145BED4068D96EA16D2D77E03D6036FC7A160EA99CDF040103DF0503161231DF060101DF070101",
                "9F0605A0000000049F2201FEDF028180A653EAC1C0F786C8724F737F172997D63D1C3251C44402049B865BAE877D0F398CBFBE8A6035E24AFA086BEFDE9351E54B95708EE672F0968BCD50DCE40F783322B2ABA04EF137EF18ABF03C7DBC5813AEAEF3AA7797BA15DF7D5BA1CBAF7FD520B5A482D8D3FEE105077871113E23A49AF3926554A70FE10ED728CF793B62A1DF03149A295B05FB390EF7923F57618A9FDA2941FC34E0DF040103DF0503161231DF060101DF070101",
        };
        try {
            int code = 0;
            long start = 0, end = 0;
            start = System.currentTimeMillis();
            MyApplication.app.emvOptV2.deleteAid(null);
            end = System.currentTimeMillis();
            Log.e(TAG, "clear aid time:" + (end - start) + "ms");

            long init = System.currentTimeMillis();
            for (int i = 0; i < aids.length; i++) {
                start = System.currentTimeMillis();
                AidV2 aidv2 = EmvUtil.hexStr2Aid(aids[i]);
                code = MyApplication.app.emvOptV2.addAid(aidv2);
                end = System.currentTimeMillis();
                if (code != 0) {
                    Log.e(TAG, "save aid[" + i + "] failed, code:" + code);
                }
                Log.e(TAG, "save aid time:" + (end - start) + "ms");
            }
            Log.e(TAG, "save aid total time:" + (end - init) + "ms");

            long capkInit = System.currentTimeMillis();
            for (int i = 0; i < capks.length; i++) {
                start = System.currentTimeMillis();
                CapkV2 capkv2 = EmvUtil.hexStr2Rid(capks[i]);
                code = MyApplication.app.emvOptV2.addCapk(capkv2);
                end = System.currentTimeMillis();
                if (code != 0) {
                    Log.e(TAG, "save capk[" + i + "] failed, code:" + code);
                }
                Log.e(TAG, "save capk time:" + (end - start) + "ms");
            }
            Log.e(TAG, "save capk total time:" + (end - capkInit) + "ms");

            Log.e(TAG, "save aid&Capk total time:" + (end - init) + "ms");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
