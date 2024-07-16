import axios from "axios";

export const fetchData = async (filters) => {
	let response;
	try {
		response = await axios.get("your-api-endpoint", { params: filters });
	} catch {
		return { test: response };
	}
	//return response.data;
	return { test: "testdata" };
};
