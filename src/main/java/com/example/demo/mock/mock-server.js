// This generates the endpoints that my system consumes.
//This is not part of the component , its only a simulation so the component 
// can run without needing the real external services when we integrate the 
// whole system we eill need to submit this
//It simulates:storage service,notification providers,energy provider
const express = require("express");
const app = express();

app.use(express.json());


// STORAGE SERVICE
app.post("/storage/auth/credentials", (req, res) => {
  res.json({ status: "saved", received: req.body });
});

app.post("/storage/audit/save", (req, res) => {
  res.json({ status: "saved", received: req.body });
});

app.get("/storage/audit/query", (req, res) => {
  res.json({
    logs: [
      {
        userId: req.query.userId || "mock-user",
        action: req.query.action || "MOCK_ACTION",
        resource: "device:123",
        details: { old: {}, new: {} },
        timestamp: "2025-12-06T10:00:00Z",
      },
    ],
  });
});


// EXTERNAL NOTIFICATION PROVIDER

app.post("/external/notifications/email", (req, res) => {
  res.json({
    status: "email_sent",
    provider: "mock-email",
    received: req.body
  });
});

app.post("/external/notifications/sms", (req, res) => {
  res.json({
    status: "sms_sent",
    provider: "mock-sms",
    received: req.body
  });
});

app.post("/external/notifications/push", (req, res) => {
  res.json({
    status: "push_sent",
    provider: "mock-push",
    received: req.body
  });
});

// EXTERNAL ENERGY PROVIDER
app.get("/provider/consumption", (req, res) => {
  res.json({
    buildingId: req.query.buildingId,
    data: [
      { timestamp: "2025-12-06T09:00:00Z", kWh: 42.5 },
      { timestamp: "2025-12-06T10:00:00Z", kWh: 39.2 },
    ],
  });
});

app.get("/provider/tariffs", (req, res) => {
  res.json({
    region: req.query.region,
    tariffs: [
      { type: "peak", price: 0.25 },
      { type: "offpeak", price: 0.11 },
    ],
  });
});

app.post("/provider/refresh-token", (req, res) => {
  res.json({
    status: "token_refreshed",
    provider: "energy_mock",
  });
});

// PORT
app.listen(9999, () => console.log("Mock server running on port 9999"));
