package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Rating;

public interface RatingDao {
	List<Rating> getRatingListByType(String type);
	Rating insertRating(String ratingId);
	Rating getRating(String userId);
}
