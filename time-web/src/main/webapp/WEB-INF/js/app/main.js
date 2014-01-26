'use strict';

angular.module('time', [
    'ui.router',
    'ngCookies',
    'LocalStorageModule']
        )
        .config(function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/dashboard");

            $stateProvider
                    .state('login', {
                        url: "/login",
                        templateUrl: "partials/login.html",
                        controller: 'LoginCtrl'
                    }).state('dashboard', {
                url: '/dashboard',
                templateUrl: 'partials/dashboard.html',
                controller: 'DashboardCtrl',
                resolve: {
                    currentUser: function(AuthService) {
                        return AuthService.getCurrentUser();
                    }
                }
            })
        });