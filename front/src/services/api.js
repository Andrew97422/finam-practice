import axios from "axios";

/**
 * Запрос на получение данных через апи ендпоинт.
 *
 * @param {Object} filters - Фильтры, которые идут в тело запроса.
 * @param {number} offset - Смещение(текущая страница) для пагинации.
 * @param {number} limit - Кол-во строк на странице.
 * @returns {Object} Данные или информация об ошибке.
 */
export const fetchData = async (filters, offset, limit) => {
	try {
		const response = await axios.post(
			`http://158.160.172.11:8080/api/v1/firms/finance_instruments?offset=${offset}&limit=${limit}`,
			filters,
			{
				headers: {
					accept: "*/*",
					"Content-Type": "application/json",
				},
			}
		);
		return {
			success: true,
			data: response.data,
		};
	} catch (error) {
		console.error(
			"Не удалось связаться с бекендом:",
			error.message || error
		);

		return {
			success: false,
			error: error.message || "Не удалось подключиться к бекенду",
		};
	}
};
