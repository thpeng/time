'use strict';

angular.module('jeapFinance', [
    'ui.router']
        )
        .config(function($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise("/customer");

            $stateProvider
                    .state('login', {
                        url: "/login",
                        templateUrl: "partials/login.html",
                        controller: 'LoginCtrl'
                    }).state('dashboard', {
                abstract: true,
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