package com.example.myapplication.restaurant.map;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Vector;

public class MapFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE=1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;
    private InfoWindow mInfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapfragment);

        mapView=findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        locationSource=
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setScaleBarEnabled(true);
        uiSettings.setZoomControlEnabled(true);
        uiSettings.setLocationButtonEnabled(true);

        markersPosition = new Vector<LatLng>();
        markersPosition.add(new LatLng(37.323982, 127.125192)); //Cafe다락다락방
        markersPosition.add(new LatLng(37.324130, 127.125635)); //위치스아일랜드
        markersPosition.add(new LatLng(37.322739, 127.124997)); //아지트커피
        markersPosition.add(new LatLng(37.323614, 127.123987)); //이디야커피
        markersPosition.add(new LatLng(37.323265, 127.124190)); //쏠레이
        markersPosition.add(new LatLng(37.322627, 127.124730)); //CAFE TRIANON
        markersPosition.add(new LatLng(37.322886, 127.124897)); //크리에이티브커피
        markersPosition.add(new LatLng(37.323184, 127.123504)); //일미닭갈비파전
        markersPosition.add(new LatLng(37.323578, 127.123110)); //손가네칼국수
        markersPosition.add(new LatLng(37.324082, 127.125437)); //엉뚱상상
        markersPosition.add(new LatLng(37.322788, 127.124614)); //고래심줄
        markersPosition.add(new LatLng(37.322960, 127.125093)); //선영이네김치짜글이
        markersPosition.add(new LatLng(37.323038, 127.123032)); //이모네산골
        markersPosition.add(new LatLng(37.323000, 127.123496)); //맛의전쟁
        markersPosition.add(new LatLng(37.323457, 127.124107)); //빨강파이프
        markersPosition.add(new LatLng(37.324303, 127.126048)); //해피덮
        markersPosition.add(new LatLng(37.324194, 127.125343)); //내가찜한닭
        markersPosition.add(new LatLng(37.325495, 127.125318)); //참바지락칼제비
        markersPosition.add(new LatLng(37.325471, 127.125308)); //홍춘
        markersPosition.add(new LatLng(37.323497, 127.124766)); //천향마라탕
        markersPosition.add(new LatLng(37.324177, 127.126076)); //사부
        markersPosition.add(new LatLng(37.323406, 127.124028)); //홍콩반점0410
        markersPosition.add(new LatLng(37.323631, 127.123232)); //호식당
        markersPosition.add(new LatLng(37.323289, 127.124328)); //아리가또맘마
        markersPosition.add(new LatLng(37.323133, 127.123946)); //우메다
        markersPosition.add(new LatLng(37.323844, 127.124592)); //겐코
        markersPosition.add(new LatLng(37.323472, 127.123763)); //미소야
        markersPosition.add(new LatLng(37.323581, 127.123218)); //브라더 양식당
        markersPosition.add(new LatLng(37.323819, 127.124607)); //그란데
        markersPosition.add(new LatLng(37.324389, 127.127083)); //다린
        markersPosition.add(new LatLng(37.324646, 127.125247)); //오블리끄
        markersPosition.add(new LatLng(37.323047, 127.123666)); //부리또 정거장
        markersPosition.add(new LatLng(37.323642, 127.124110)); //도스마스
        markersPosition.add(new LatLng(36.834358, 127.172947)); //마리스커피
        markersPosition.add(new LatLng(36.834109, 127.172919)); //카페고메
        markersPosition.add(new LatLng(36.835781, 127.173039)); //지로스터
        markersPosition.add(new LatLng(36.835273, 127.173030)); //피플앤스토리
        markersPosition.add(new LatLng(36.836470, 127.173287)); //GRAVITY LAKE
        markersPosition.add(new LatLng(36.835758, 127.173034)); //카페라이크
        markersPosition.add(new LatLng(36.835756, 127.173029)); //슬로우커피2
        markersPosition.add(new LatLng(36.833732, 127.173131)); //천호지
        markersPosition.add(new LatLng(36.832836, 127.174726)); //원조부안집
        markersPosition.add(new LatLng(36.832560, 127.176334)); //은하철도곱곱곱
        markersPosition.add(new LatLng(36.832921, 127.175505)); //수업이 끝난 오후
        markersPosition.add(new LatLng(36.832717, 127.176053)); //참새방
        markersPosition.add(new LatLng(36.833731, 127.173129)); //동춘옥
        markersPosition.add(new LatLng(36.833183, 127.174667)); //하이린
        markersPosition.add(new LatLng(36.831628, 127.177178)); //흥부반점
        markersPosition.add(new LatLng(36.834094, 127.173418)); //스시마당
        markersPosition.add(new LatLng(36.840686, 127.174869)); //돈까스이야기
        markersPosition.add(new LatLng(36.830468, 127.161163)); //유생촌
        markersPosition.add(new LatLng(36.832512, 127.174397)); //돈까스마을
        markersPosition.add(new LatLng(36.838641, 127.174046)); //성탄수제버거앤갈비
        markersPosition.add(new LatLng(36.832772, 127.175674)); //SUBMEAL
        markersPosition.add(new LatLng(36.831173, 127.164242)); //BABALAB


        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return (CharSequence)infoWindow.getMarker().getTag();
            }
        });

        Marker marker1 = new Marker();
        Marker marker2 = new Marker();
        Marker marker3 = new Marker();
        Marker marker4 = new Marker();
        Marker marker5 = new Marker();
        Marker marker6 = new Marker();
        Marker marker7 = new Marker();
        Marker marker8 = new Marker();
        Marker marker9 = new Marker();
        Marker marker10 = new Marker();
        Marker marker11 = new Marker();
        Marker marker12 = new Marker();
        Marker marker13 = new Marker();
        Marker marker14 = new Marker();
        Marker marker15 = new Marker();
        Marker marker16 = new Marker();
        Marker marker17 = new Marker();
        Marker marker18 = new Marker();
        Marker marker19 = new Marker();
        Marker marker20 = new Marker();
        Marker marker21 = new Marker();
        Marker marker22 = new Marker();
        Marker marker23 = new Marker();
        Marker marker24 = new Marker();
        Marker marker25 = new Marker();
        Marker marker26 = new Marker();
        Marker marker27 = new Marker();
        Marker marker28 = new Marker();
        Marker marker29 = new Marker();
        Marker marker30 = new Marker();
        Marker marker31 = new Marker();
        Marker marker32 = new Marker();
        Marker marker33 = new Marker();
        Marker marker34 = new Marker();
        Marker marker35 = new Marker();
        Marker marker36 = new Marker();
        Marker marker37 = new Marker();
        Marker marker38 = new Marker();
        Marker marker39 = new Marker();
        Marker marker40 = new Marker();
        Marker marker41 = new Marker();
        Marker marker42 = new Marker();
        Marker marker43 = new Marker();
        Marker marker44 = new Marker();
        Marker marker45 = new Marker();
        Marker marker46 = new Marker();
        Marker marker47 = new Marker();
        Marker marker48 = new Marker();
        Marker marker49 = new Marker();
        Marker marker50 = new Marker();
        Marker marker51 = new Marker();
        Marker marker52 = new Marker();
        Marker marker53 = new Marker();
        Marker marker54 = new Marker();
        Marker marker55 = new Marker();

        marker1.setPosition(new LatLng(37.323982, 127.125192)); //Cafe다락다락방
        marker1.setMap(naverMap);
        marker2.setPosition(new LatLng(37.324130, 127.125635)); //위치스아일랜드
        marker2.setMap(naverMap);
        marker3.setPosition(new LatLng(37.322739, 127.124997)); //아지트커피
        marker3.setMap(naverMap);
        marker4.setPosition(new LatLng(37.323614, 127.123987)); //이디야커피
        marker4.setMap(naverMap);
        marker5.setPosition(new LatLng(37.323265, 127.124190)); //쏠레이
        marker5.setMap(naverMap);
        marker6.setPosition(new LatLng(37.322627, 127.124730)); //CAFE TRIANON
        marker6.setMap(naverMap);
        marker7.setPosition(new LatLng(37.322886, 127.124897)); //크리에이티브커피
        marker7.setMap(naverMap);
        marker8.setPosition(new LatLng(37.323184, 127.123504)); //일미닭갈비파전
        marker8.setMap(naverMap);
        marker9.setPosition(new LatLng(37.323578, 127.123110)); //손가네칼국수
        marker9.setMap(naverMap);
        marker10.setPosition(new LatLng(37.324082, 127.125437)); //엉뚱상상
        marker10.setMap(naverMap);
        marker11.setPosition(new LatLng(37.322788, 127.124614)); //고래심줄
        marker11.setMap(naverMap);
        marker12.setPosition(new LatLng(37.322960, 127.125093)); //선영이네김치짜글이
        marker12.setMap(naverMap);
        marker13.setPosition(new LatLng(37.323038, 127.123032)); //이모네산골
        marker13.setMap(naverMap);
        marker14.setPosition(new LatLng(37.323000, 127.123496)); //맛의전쟁
        marker14.setMap(naverMap);
        marker15.setPosition(new LatLng(37.323457, 127.124107)); //빨강파이프
        marker15.setMap(naverMap);
        marker16.setPosition(new LatLng(37.324303, 127.126048)); //해피덮
        marker16.setMap(naverMap);
        marker17.setPosition(new LatLng(37.324194, 127.125343)); //내가찜한닭
        marker17.setMap(naverMap);
        marker18.setPosition(new LatLng(37.325495, 127.125318)); //참바지락칼제비
        marker18.setMap(naverMap);
        marker19.setPosition(new LatLng(37.325471, 127.125308)); //홍춘
        marker19.setMap(naverMap);
        marker20.setPosition(new LatLng(37.323497, 127.124766)); //천향마라탕
        marker20.setMap(naverMap);
        marker21.setPosition(new LatLng(37.324177, 127.126076)); //사부
        marker21.setMap(naverMap);
        marker22.setPosition(new LatLng(37.323406, 127.124028)); //홍콩반점0410
        marker22.setMap(naverMap);
        marker23.setPosition(new LatLng(37.323631, 127.123232)); //호식당
        marker23.setMap(naverMap);
        marker24.setPosition(new LatLng(37.323289, 127.124328)); //아리가또맘마
        marker24.setMap(naverMap);
        marker25.setPosition(new LatLng(37.323133, 127.123946)); //우메다
        marker25.setMap(naverMap);
        marker26.setPosition(new LatLng(37.323844, 127.124592)); //겐코
        marker26.setMap(naverMap);
        marker27.setPosition(new LatLng(37.323472, 127.123763)); //미소야
        marker27.setMap(naverMap);
        marker28.setPosition(new LatLng(37.323581, 127.123218)); //브라더 양식당
        marker28.setMap(naverMap);
        marker29.setPosition(new LatLng(37.323819, 127.124607)); //그란데
        marker29.setMap(naverMap);
        marker30.setPosition(new LatLng(37.324389, 127.127083)); //다린
        marker30.setMap(naverMap);
        marker31.setPosition(new LatLng(37.324646, 127.125247)); //오블리끄
        marker31.setMap(naverMap);
        marker32.setPosition(new LatLng(37.323047, 127.123666)); //부리또 정거장
        marker32.setMap(naverMap);
        marker33.setPosition(new LatLng(37.323642, 127.124110)); //도스마스
        marker33.setMap(naverMap);
        marker34.setPosition(new LatLng(36.834358, 127.172947)); //마리스커피
        marker34.setMap(naverMap);
        marker35.setPosition(new LatLng(36.834109, 127.172919)); //카페고메
        marker35.setMap(naverMap);
        marker36.setPosition(new LatLng(36.835781, 127.173039)); //지로스터
        marker36.setMap(naverMap);
        marker37.setPosition(new LatLng(36.835273, 127.173030)); //피플앤스토리
        marker37.setMap(naverMap);
        marker38.setPosition(new LatLng(36.836470, 127.173287)); //GRAVITY LAKE
        marker38.setMap(naverMap);
        marker39.setPosition(new LatLng(36.835758, 127.173034)); //카페라이크
        marker39.setMap(naverMap);
        marker40.setPosition(new LatLng(36.835756, 127.173029)); //슬로우커피2
        marker40.setMap(naverMap);
        marker41.setPosition(new LatLng(36.833732, 127.173131)); //천호지
        marker41.setMap(naverMap);
        marker42.setPosition(new LatLng(36.832836, 127.174726)); //원조부안집
        marker42.setMap(naverMap);
        marker43.setPosition(new LatLng(36.832560, 127.176334)); //은하철도곱곱곱
        marker43.setMap(naverMap);
        marker44.setPosition(new LatLng(36.832921, 127.175505)); //수업이 끝난 오후
        marker44.setMap(naverMap);
        marker45.setPosition(new LatLng(36.832717, 127.176053)); //참새방
        marker45.setMap(naverMap);
        marker46.setPosition(new LatLng(36.833731, 127.173129)); //동춘옥
        marker46.setMap(naverMap);
        marker47.setPosition(new LatLng(36.833183, 127.174667)); //하이린
        marker47.setMap(naverMap);
        marker48.setPosition(new LatLng(36.831628, 127.177178)); //흥부반점
        marker48.setMap(naverMap);
        marker49.setPosition(new LatLng(36.834094, 127.173418)); //스시마당
        marker49.setMap(naverMap);
        marker50.setPosition(new LatLng(36.840686, 127.174869)); //돈까스이야기
        marker50.setMap(naverMap);
        marker51.setPosition(new LatLng(36.830468, 127.161163)); //유생촌
        marker51.setMap(naverMap);
        marker52.setPosition(new LatLng(36.832512, 127.174397)); //돈까스마을
        marker52.setMap(naverMap);
        marker53.setPosition(new LatLng(36.838641, 127.174046)); //성탄수제버거앤갈비
        marker53.setMap(naverMap);
        marker54.setPosition(new LatLng(36.832772, 127.175674)); //SUBMEAL
        marker54.setMap(naverMap);
        marker55.setPosition(new LatLng(36.831173, 127.164242)); //BABALAB
        marker55.setMap(naverMap);

// 지도를 클릭하면 정보 창을 닫음
        naverMap.setOnMapClickListener((coord, point) -> {
            infoWindow.close();
        });

// 마커를 클릭하면:
        Overlay.OnClickListener listener = overlay -> {
            Marker marker = (Marker)overlay;

            if (marker.getInfoWindow() == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindow.open(marker);
            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindow.close();
            }

            return true;
        };

        marker1.setOnClickListener(listener);
        marker2.setOnClickListener(listener);
        marker3.setOnClickListener(listener);
        marker4.setOnClickListener(listener);
        marker5.setOnClickListener(listener);
        marker6.setOnClickListener(listener);
        marker7.setOnClickListener(listener);
        marker8.setOnClickListener(listener);
        marker9.setOnClickListener(listener);
        marker10.setOnClickListener(listener);
        marker11.setOnClickListener(listener);
        marker12.setOnClickListener(listener);
        marker13.setOnClickListener(listener);
        marker14.setOnClickListener(listener);
        marker15.setOnClickListener(listener);
        marker16.setOnClickListener(listener);
        marker17.setOnClickListener(listener);
        marker18.setOnClickListener(listener);
        marker19.setOnClickListener(listener);
        marker20.setOnClickListener(listener);
        marker21.setOnClickListener(listener);
        marker22.setOnClickListener(listener);
        marker23.setOnClickListener(listener);
        marker24.setOnClickListener(listener);
        marker25.setOnClickListener(listener);
        marker26.setOnClickListener(listener);
        marker27.setOnClickListener(listener);
        marker28.setOnClickListener(listener);
        marker29.setOnClickListener(listener);
        marker30.setOnClickListener(listener);
        marker31.setOnClickListener(listener);
        marker32.setOnClickListener(listener);
        marker33.setOnClickListener(listener);
        marker34.setOnClickListener(listener);
        marker35.setOnClickListener(listener);
        marker36.setOnClickListener(listener);
        marker37.setOnClickListener(listener);
        marker38.setOnClickListener(listener);
        marker39.setOnClickListener(listener);
        marker40.setOnClickListener(listener);
        marker41.setOnClickListener(listener);
        marker42.setOnClickListener(listener);
        marker43.setOnClickListener(listener);
        marker44.setOnClickListener(listener);
        marker45.setOnClickListener(listener);
        marker46.setOnClickListener(listener);
        marker47.setOnClickListener(listener);
        marker48.setOnClickListener(listener);
        marker49.setOnClickListener(listener);
        marker50.setOnClickListener(listener);
        marker51.setOnClickListener(listener);
        marker52.setOnClickListener(listener);
        marker53.setOnClickListener(listener);
        marker54.setOnClickListener(listener);
        marker55.setOnClickListener(listener);

        marker1.setTag("Cafe다락다락방");
        marker1.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker1);
            return true;
        });

        marker2.setTag("위치스아일랜드");
        marker2.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker2);
            return true;
        });

        marker3.setTag("아지트커피");
        marker3.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker3);
            return true;
        });

        marker4.setTag("이디야커피");
        marker4.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker4);
            return true;
        });

        marker5.setTag("쏠레이");
        marker5.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker5);
            return true;
        });

        marker6.setTag("CAFE TRIANON");
        marker6.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker6);
            return true;
        });

        marker7.setTag("크리에이티브커피");
        marker7.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker7);
            return true;
        });

        marker8.setTag("일미닭갈비파전");
        marker8.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker8);
            return true;
        });

        marker9.setTag("손가네칼국수");
        marker9.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker9);
            return true;
        });

        marker10.setTag("엉뚱상상");
        marker10.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker10);
            return true;
        });

        marker11.setTag("고래심줄");
        marker11.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker11);
            return true;
        });

        marker12.setTag("선영이네김치짜글이");
        marker12.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker12);
            return true;
        });

        marker13.setTag("이모네산골");
        marker13.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker13);
            return true;
        });

        marker14.setTag("맛의전쟁");
        marker14.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker14);
            return true;
        });

        marker15.setTag("빨강파이프");
        marker15.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker15);
            return true;
        });

        marker16.setTag("해피덮");
        marker16.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker16);
            return true;
        });

        marker17.setTag("내가찜한닭");
        marker17.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker17);
            return true;
        });

        marker18.setTag("참바지락칼제비");
        marker18.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker18);
            return true;
        });

        marker19.setTag("홍춘");
        marker19.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker19);
            return true;
        });

        marker20.setTag("천향마라탕");
        marker20.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker20);
            return true;
        });

        marker21.setTag("사부");
        marker21.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker21);
            return true;
        });

        marker22.setTag("홍콩반점0410");
        marker22.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker22);
            return true;
        });

        marker23.setTag("호식당");
        marker23.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker23);
            return true;
        });

        marker24.setTag("아리가또맘마");
        marker24.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker24);
            return true;
        });

        marker25.setTag("우메다");
        marker25.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker25);
            return true;
        });

        marker26.setTag("겐코");
        marker26.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker26);
            return true;
        });

        marker27.setTag("미소야");
        marker27.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker27);
            return true;
        });

        marker28.setTag("브라더 양식당");
        marker28.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker28);
            return true;
        });

        marker29.setTag("그란데");
        marker29.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker29);
            return true;
        });

        marker30.setTag("다린");
        marker30.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker30);
            return true;
        });

        marker31.setTag("오블리끄");
        marker31.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker31);
            return true;
        });

        marker32.setTag("부리또 정거장");
        marker32.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker32);
            return true;
        });

        marker33.setTag("도스마스");
        marker33.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker33);
            return true;
        });

        marker34.setTag("마리스커피");
        marker34.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker34);
            return true;
        });

        marker35.setTag("카페고메");
        marker35.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker35);
            return true;
        });

        marker36.setTag("지로스터");
        marker36.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker36);
            return true;
        });

        marker37.setTag("피플앤스토리");
        marker37.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker37);
            return true;
        });

        marker38.setTag("GRAVITY LAKE");
        marker38.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker38);
            return true;
        });

        marker39.setTag("카페라이크");
        marker39.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker39);
            return true;
        });

        marker40.setTag("슬로우커피2");
        marker40.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker40);
            return true;
        });

        marker41.setTag("천호지");
        marker41.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker41);
            return true;
        });

        marker42.setTag("원조부안집");
        marker42.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker42);
            return true;
        });

        marker43.setTag("은하철도곱곱곱");
        marker43.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker43);
            return true;
        });

        marker44.setTag("수업이 끝난 오후");
        marker44.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker44);
            return true;
        });

        marker45.setTag("참새방");
        marker45.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker45);
            return true;
        });

        marker46.setTag("동춘옥");
        marker46.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker46);
            return true;
        });

        marker47.setTag("하이린");
        marker47.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker47);
            return true;
        });

        marker48.setTag("흥부반점");
        marker48.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker48);
            return true;
        });

        marker49.setTag("스시마당");
        marker49.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker49);
            return true;
        });

        marker50.setTag("돈까스이야기");
        marker50.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker50);
            return true;
        });

        marker51.setTag("유생촌");
        marker51.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker51);
            return true;
        });

        marker52.setTag("돈까스마을");
        marker52.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker52);
            return true;
        });

        marker53.setTag("성탄수제버거앤갈비");
        marker53.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker53);
            return true;
        });

        marker54.setTag("SUBMEAL");
        marker54.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker54);
            return true;
        });

        marker55.setTag("BABALAB");
        marker55.setOnClickListener(overlay -> {
            // 마커를 클릭할 때 정보창을 엶
            infoWindow.open(marker55);
            return true;
        });

        // 카메라 이동 되면 호출 되는 이벤트
        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(int reason, boolean animated) {
                freeActiveMarkers();
                // 정의된 마커위치들중 가시거리 내에있는것들만 마커 생성
                LatLng currentPosition = getCurrentPosition(naverMap);
                for (LatLng markerPosition: markersPosition) {
                    if (!withinSightMarker(currentPosition, markerPosition))
                        continue;
                    Marker marker = new Marker();
                    marker.setPosition(markerPosition);
                    marker.setMap(naverMap);
                    activeMarkers.add(marker);
                }
            }
        });
    }

    // 마커 정보 저장시킬 변수들 선언
    private Vector<LatLng> markersPosition;
    private Vector<Marker> activeMarkers;

    // 현재 카메라가 보고있는 위치
    public LatLng getCurrentPosition(NaverMap naverMap) {
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        return new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }

    // 선택한 마커의 위치가 가시거리(카메라가 보고있는 위치 반경 3km 내)에 있는지 확인
    public final static double REFERANCE_LAT = 1 / 109.958489129649955;
    public final static double REFERANCE_LNG = 1 / 88.74;
    public final static double REFERANCE_LAT_X3 = 3 / 109.958489129649955;
    public final static double REFERANCE_LNG_X3 = 3 / 88.74;
    public boolean withinSightMarker(LatLng currentPosition, LatLng markerPosition) {
        boolean withinSightMarkerLat = Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT;
        boolean withinSightMarkerLng = Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG;
        return withinSightMarkerLat && withinSightMarkerLng;
    }

    // 지도상에 표시되고있는 마커들 지도에서 삭제
    private void freeActiveMarkers() {
        if (activeMarkers == null) {
            activeMarkers = new Vector<Marker>();
            return;
        }
        for (Marker activeMarker: activeMarkers) {
            activeMarker.setMap(null);
        }
        activeMarkers = new Vector<Marker>();
    }


    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults){
        if(locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }
}