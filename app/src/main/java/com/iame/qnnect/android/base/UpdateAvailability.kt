package com.iame.qnnect.android.base

annotation class UpdateAvailability {
    companion object {
        var UNKNOWN = 0
        var UPDATE_NOT_AVAILABLE = 1
        var UPDATE_AVAILABLE = 2
        var DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS = 3
    }
}

annotation class InstallStatus {
    companion object {
        var UNKNOWN = 0
        var REQUIRES_UI_INTENT = 10
        var PENDING = 1
        var DOWNLOADING = 2
        var DOWNLOADED = 11
        var INSTALLING = 3
        var INSTALLED = 4
        var FAILED = 5
        var CANCELED = 6
    }
}

/**
 * 업데이트가 없는 경우
 * UpdateAvailability : 1
 * installStatus : 0
 *
 *
 * 권장 업데이트 테스트
 *
 * 업데이트가 있는 경우
 * UpdateAvailability : 2
 * installStatus : 0
 *
 * 업데이트 확인 후 진행 중
 * UpdateAvailability : 3
 * installStatus : 2
 *
 * 업데이트 다운로드 완료
 * UpdateAvailability : 3
 * installStatus : 11
 *
 * 업데이트 설치/재시작 진행 중
 * (뒤로 가기 및 화면 종료를 해도 설치가 완료되면 앱을 자동 재실행 합니다.)
 * UpdateAvailability : 3
 * installStatus : 3
 *
 * 업데이트 설치 및 재시작 완료
 * UpdateAvailability : 1
 * installStatus : 0
 *
 *
 * 즉시 업데이트 테스트
 *
 * 업데이트가 있는 경우
 * UpdateAvailability : 2
 * installStatus : 0
 *
 * 업데이트 확인 후 진행 중(확인 클릭 후 뒤로가기)
 * UpdateAvailability : 3
 * installStatus : 2
 *
 * 업데이트 다운로드 완료
 * (설치 화면을 벗어나면 자동으로 재실행 되지 않습니다.)
 * UpdateAvailability : 3
 * installStatus : 11
 *
 * 업데이트 설치 및 재시작 완료
 * UpdateAvailability : 1
 * installStatus : 0
 *
 */