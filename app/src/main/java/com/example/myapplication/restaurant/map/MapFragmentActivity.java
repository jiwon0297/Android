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
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Vector;

public class MapFragmentActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE=1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;

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
        markersPosition.add(new LatLng(37.324048, 127.125056)); //Cafe다락다락방
        markersPosition.add(new LatLng(37.323863,127.1242992)); //위치스아일랜드
        markersPosition.add(new LatLng(37.322791, 127.124997)); //아지트커피
        markersPosition.add(new LatLng(37.323569, 127.123933)); //이디야커피
        markersPosition.add(new LatLng(37.323337, 127.124172)); //쏠레이
        markersPosition.add(new LatLng(37.322785, 127.124700)); //CAFE TRIANON
        markersPosition.add(new LatLng(37.322936, 127.124883)); //크리에이티브커피
        markersPosition.add(new LatLng(37.323215, 127.123435)); //일미닭갈비파전
        markersPosition.add(new LatLng(37.323598, 127.123226)); //손가네칼국수
        markersPosition.add(new LatLng(37.324104, 127.125433)); //엉뚱상상
        markersPosition.add(new LatLng(37.323002, 127.124560)); //고래심줄
        markersPosition.add(new LatLng(37.322992, 127.125071)); //선영이네김치짜글이
        markersPosition.add(new LatLng(37.323113, 127.123051)); //이모네산골
        markersPosition.add(new LatLng(37.323079, 127.123522)); //맛의전쟁
        markersPosition.add(new LatLng(37.323526, 127.124231)); //빨강파이프
        markersPosition.add(new LatLng(37.324306, 127.125981)); //해피덮
        markersPosition.add(new LatLng(37.324303, 127.125422)); //내가찜한닭
        markersPosition.add(new LatLng(37.325495, 127.125318)); //참바지락칼제비
        markersPosition.add(new LatLng(37.325471, 127.125308)); //홍춘
        markersPosition.add(new LatLng(37.323494, 127.124765)); //천향마라탕
        markersPosition.add(new LatLng(37.324192, 127.126048)); //사부
        markersPosition.add(new LatLng(37.323438, 127.124013)); //홍콩반점0410
        markersPosition.add(new LatLng(37.323631, 127.123232)); //호식당
        markersPosition.add(new LatLng(37.323289, 127.124328)); //아리가또맘마
        markersPosition.add(new LatLng(37.323173, 127.123912)); //우메다
        markersPosition.add(new LatLng(37.323922, 127.124462)); //겐코
        markersPosition.add(new LatLng(37.323472, 127.123763)); //미소야
        markersPosition.add(new LatLng(37.323609, 127.123182)); //브라더 양식당
        markersPosition.add(new LatLng(37.323735, 127.124693)); //그란데
        markersPosition.add(new LatLng(37.324368, 127.127040)); //다린
        markersPosition.add(new LatLng(37.324612, 127.125314)); //오블리끄
        markersPosition.add(new LatLng(37.323003, 127.123629)); //부리또 정거장
        markersPosition.add(new LatLng(37.323618, 127.124013)); //도스마스
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
        boolean withinSightMarkerLat = Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X3;
        boolean withinSightMarkerLng = Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X3;
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