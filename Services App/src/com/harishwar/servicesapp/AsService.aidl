package com.harishwar.servicesapp;

import com.harishwar.servicesapp.AsServiceCallback;

interface AsService {
	oneway void setCallback(AsServiceCallback callback, String url);
}